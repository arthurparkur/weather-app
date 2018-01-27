package weatherapp.arthurdanilov92.gmail.com.weatherapp;

import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.InputType;
import android.text.TextUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

  private final Handler handler   = new Handler();
  private final String  PREF_FILE = "WeatherPref";

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.drawer_activity_main);
    Toolbar toolbar = findViewById(R.id.toolbar);
    setSupportActionBar(toolbar);

    DrawerLayout drawer = findViewById(R.id.drawer_layout);
    ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
            this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
    drawer.addDrawerListener(toggle);
    toggle.syncState();

    NavigationView navigationView = findViewById(R.id.nav_view);
    navigationView.setNavigationItemSelectedListener(new NavDrawerListener(this));

    /*String cityName = loadCityNameFromSharedPreferences();
    if (!TextUtils.isEmpty(cityName)) changeCity(cityName);*/
  }

  @Override
  public void onBackPressed() {
    DrawerLayout drawer = findViewById(R.id.drawer_layout);
    if (drawer.isDrawerOpen(GravityCompat.START)) {
      drawer.closeDrawer(GravityCompat.START);
    } else {
      super.onBackPressed();
    }
  }

  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
    getMenuInflater().inflate(R.menu.main, menu);
    return true;
  }

  @Override
  public boolean onOptionsItemSelected(MenuItem item) {
    int id = item.getItemId();

    if (id == R.id.choose_city_btn) {
      showInputDialog();
      return true;
    }
    return false;
  }

  private void showInputDialog() {
    AlertDialog.Builder builder = new AlertDialog.Builder(this);
    builder.setTitle(getString(R.string.enter_city_name_dialog));
    final EditText input = new EditText(this);
    input.setInputType(InputType.TYPE_CLASS_TEXT);
    builder.setView(input);
    builder.setPositiveButton("Search", new DialogInterface.OnClickListener() {
      @Override
      public void onClick(DialogInterface dialog, int which) {
        String cityName = input.getText().toString();
        if (!TextUtils.isEmpty(cityName)) changeCity(cityName);
        else Toast.makeText(getApplicationContext(),
                            getString(R.string.empty_city_name),
                            Toast.LENGTH_LONG).show();
      }
    });
    builder.show();
  }

  public void changeCity(String city) {
    updateWeatherData(city);
  }

  private void updateWeatherData(final String city) {
    new Thread() {
      public void run() {
        final JSONObject json = WeatherDataLoader.getJSONData(getApplicationContext(), city);
        if (json == null) {
          handler.post(new Runnable() {
            public void run() {
              Toast.makeText(getApplicationContext(), getString(R.string.place_not_found),
                             Toast.LENGTH_LONG).show();
            }
          });
        } else {
          handler.post(new Runnable() {
            public void run() {
              renderWeather(json);
              saveCityNameToSharedPreferences(city);
            }
          });
        }
      }
    }.start();
  }

  private void renderWeather(JSONObject json) {
    Log.d("RenderWeatherMethodRun", "json " + json.toString());
    try {
      Gson       gson          = new GsonBuilder().serializeNulls().create();
      WeatherMap weatherObject = gson.fromJson(json.toString(), WeatherMap.class);
      String     cityName      = weatherObject.getName();
      Integer    temperature   = weatherObject.getTemperature();

      TextView cityNameView    = findViewById(R.id.city_name);
      TextView temperatureView = findViewById(R.id.today_temperature);
      cityNameView.setText(cityName);
      temperatureView.setText(temperature);
    } catch (Exception e) {
      Log.d("Catch RenderWeather", e.getMessage());
    }
  }

  private void saveCityNameToSharedPreferences(String city) {
    SharedPreferences sp = getSharedPreferences(PREF_FILE, Context.MODE_PRIVATE);

    SharedPreferences.Editor spEditor = sp.edit();
    spEditor.putString(getString(R.string.sp_city_key), city);
    spEditor.apply();
  }

  private String loadCityNameFromSharedPreferences() {
    SharedPreferences sp = getSharedPreferences(PREF_FILE, Context.MODE_PRIVATE);
    return sp.getString(getString(R.string.sp_city_key), "");

  }
}
