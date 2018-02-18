package weatherapp.arthurdanilov92.gmail.com.weatherapp;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
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
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import weatherapp.arthurdanilov92.gmail.com.weatherapp.db.DataBaseService;
import weatherapp.arthurdanilov92.gmail.com.weatherapp.models.WeatherModel;
import weatherapp.arthurdanilov92.gmail.com.weatherapp.models.WeatherWeekModel;

public class MainActivity extends AppCompatActivity {

  private final String PREF_FILE      = "WeatherPref";
  private final int    OLD_DATE_LIMIT = 10800000;
  DataBaseService dataBaseService;

  @BindView(R.id.toolbar)
  Toolbar        toolbar;
  @BindView(R.id.drawer_layout)
  DrawerLayout   drawer;
  @BindView(R.id.nav_view)
  NavigationView navigationView;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.drawer_activity_main);
    ButterKnife.bind(this);

    setSupportActionBar(toolbar);

    ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
            this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
    drawer.addDrawerListener(toggle);
    toggle.syncState();

    navigationView.setNavigationItemSelectedListener(new NavDrawerListener(this));

    dataBaseService = new DataBaseService(getApplicationContext());
    dataBaseService.open();

    String cityName = loadCityNameFromSharedPreferences();
    if (!TextUtils.isEmpty(cityName)) {
      getWeatherData(cityName);
      getWeatherWeekData(cityName);
    }
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
        if (!TextUtils.isEmpty(cityName)) {
          getWeatherData(cityName);
          getWeatherWeekData(cityName);
        } else Toast.makeText(getApplicationContext(),
                            getString(R.string.empty_city_name),
                            Toast.LENGTH_LONG).show();
      }
    });
    builder.show();
  }

  private void saveCityNameToSharedPreferences(String city) {
    SharedPreferences        sp       = getSharedPreferences(PREF_FILE, Context.MODE_PRIVATE);
    SharedPreferences.Editor spEditor = sp.edit();
    spEditor.putString(getString(R.string.sp_city_key), city);
    spEditor.apply();
  }

  private String loadCityNameFromSharedPreferences() {
    SharedPreferences sp = getSharedPreferences(PREF_FILE, Context.MODE_PRIVATE);
    return sp.getString(getString(R.string.sp_city_key), "");
  }

  public void getWeatherData(final String cityName) {
    WeatherModel weatherObj = dataBaseService.getWeatherData(cityName);
    if (weatherObj != null &&
            weatherObj.getUpdatedDate() + OLD_DATE_LIMIT < ((new Date()).getTime())) {
      WeatherStorage.setWeatherObjSingleton(weatherObj);
    } else loadWeather(cityName);
  }

  public void getWeatherWeekData(final String cityName) {
    WeatherWeekModel weatherObj = dataBaseService.getWeatherWeekData(cityName);
    if (weatherObj != null && weatherObj.getUpdatedDate() + OLD_DATE_LIMIT < ((new Date()).getTime())) {
      WeatherStorage.setWeatherWeekObjSingleton(weatherObj);
    } else loadWeatherForWeek(cityName);
  }

  public void loadWeather(final String cityName) {
    App.getWeatherApi()
            .getWeatherByCityName(cityName, App.appKey)
            .enqueue(new Callback<WeatherModel>() {
              @Override
              public void onResponse(Call<WeatherModel> call, Response<WeatherModel> response) {
                WeatherModel weatherObj = response.body();
                if (weatherObj != null) {
                  Log.d("api/data/2.5/weather", " : success");
                  WeatherStorage.setWeatherObjSingleton(weatherObj);
                  saveCityNameToSharedPreferences(cityName);
                  dataBaseService.addOrUpdateWeatherEntry(weatherObj);
                } else Log.d("api/data/2.5/weather", " : empty request body");
              }

              @Override
              public void onFailure(Call<WeatherModel> call, Throwable t) {
                Log.d("api/data/2.5/weather", t.getMessage());
              }
            });
  }

  public void loadWeatherForWeek(final String cityName) {
    App.getWeatherApi()
            .getWeatherWeekByCityName(cityName, App.appKey)
            .enqueue(new Callback<WeatherWeekModel>() {
              @Override
              public void onResponse(Call<WeatherWeekModel> call,
                                     Response<WeatherWeekModel> response) {
                WeatherWeekModel weatherObj = response.body();
                if (weatherObj != null) {
                  Log.d("data/2.5/forecast/daily", " : success");
                  WeatherStorage.setWeatherWeekObjSingleton(weatherObj);
                  dataBaseService.addOrUpdateWeatherWeekEntry(weatherObj);
                } else Log.d("data/2.5/forecast/daily", " : empty request body");
              }

              @Override
              public void onFailure(Call<WeatherWeekModel> call, Throwable t) {
                Log.d("data/2.5/forecast/daily", t.getMessage());
              }
            });
  }

  @OnClick(R.id.floating_action_btn)
  public void onClick(View v) {
    shareWeather();
  }

  public void shareWeather() {
    if (WeatherStorage.getWeatherObjSingleton() != null) {
      Intent intent = new Intent(Intent.ACTION_SEND);
      intent.setType("text/plain");
      intent.putExtra(Intent.EXTRA_TEXT, WeatherStorage.getWeatherObjSingleton().getAndFormatWeatherInfo());
      String title   = "Share";
      Intent chooser = Intent.createChooser(intent, title);
      startActivity(chooser);
    } else Toast.makeText(getApplicationContext(),
                          getString(R.string.empty_city),
                          Toast.LENGTH_LONG).show();
  }
}
