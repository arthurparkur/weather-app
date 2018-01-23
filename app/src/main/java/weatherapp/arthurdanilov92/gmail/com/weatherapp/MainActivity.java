package weatherapp.arthurdanilov92.gmail.com.weatherapp;

import android.content.DialogInterface;
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
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.Gson;

import org.json.JSONObject;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

  private final Handler handler = new Handler();

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.drawer_activity_main);
    Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
    setSupportActionBar(toolbar);

    DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
    ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
            this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
    drawer.addDrawerListener(toggle);
    toggle.syncState();

    NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
    navigationView.setNavigationItemSelectedListener(this);
  }

  @Override
  public void onBackPressed() {
    DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
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
    builder.setTitle(getString(R.string.change_city_dialog));
    final EditText input = new EditText(this);
    input.setInputType(InputType.TYPE_CLASS_TEXT);
    builder.setView(input);
    builder.setPositiveButton("Seatch", new DialogInterface.OnClickListener() {
      @Override
      public void onClick(DialogInterface dialog, int which) {
        changeCity(input.getText().toString());
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
        // Вызов методов напрямую может вызвать runtime error
        // Мы не можем напрямую обновить UI, поэтому используем handler, чтобы обновить интерфейс в главном потоке.
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
            }
          });
        }
      }
    }.start();
  }

  private void renderWeather(JSONObject json) {
    Log.d("RenderWeatherMethodRun", "json " + json.toString());
    try {
      WeatherMap map = new Gson().fromJson(json.toString(), WeatherMap.class);
    } catch (Exception e) {
      Log.d("Catch RenderWeather", e.getMessage());
    }
  }

  @SuppressWarnings("StatementWithEmptyBody")
  @Override
  public boolean onNavigationItemSelected(MenuItem item) {
    int id = item.getItemId();

    int targetId = R.layout.content_main;

    if (id == R.id.weather_today) {
      targetId = R.layout.weather_today;
    } else if (id == R.id.weather_week) {
      targetId = R.layout.weather_week;
    } else if (id == R.id.contacts) {
      targetId = R.layout.contacts;
    } else if (id == R.id.help) {
      targetId = R.layout.help;
    }

    ViewGroup parent = (ViewGroup) findViewById(R.id.content_main);
    parent.removeAllViews();
    View targetContent = getLayoutInflater().inflate(targetId, parent, false);
    parent.addView(targetContent);

    DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
    drawer.closeDrawer(GravityCompat.START);
    return true;
  }
}
