package weatherapp.arthurdanilov92.gmail.com.weatherapp;

import android.content.Context;
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
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.io.IOException;
import java.util.Date;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import weatherapp.arthurdanilov92.gmail.com.weatherapp.db.DataBaseService;
import weatherapp.arthurdanilov92.gmail.com.weatherapp.models.WeatherIntegrate;
import weatherapp.arthurdanilov92.gmail.com.weatherapp.models.WeatherModel;
import weatherapp.arthurdanilov92.gmail.com.weatherapp.models.WeatherWeekModel;

public class MainActivity extends AppCompatActivity {

  private final String PREF_FILE      = "WeatherPref";
  private final int    OLD_DATE_LIMIT = 10800000;
  DataBaseService   dataBaseService;
  NavDrawerListener navDrawerListener;

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

    navDrawerListener = new NavDrawerListener(this);
    navDrawerListener.initFirstFragment();
    navigationView.setNavigationItemSelectedListener(navDrawerListener);

    dataBaseService = new DataBaseService(getApplicationContext());
    dataBaseService.open();

    String cityName = loadCityNameFromSharedPreferences();
    if (!TextUtils.isEmpty(cityName)) {
      getWeahterData(cityName);
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
    builder.setPositiveButton("Search", (dialog, which) -> {
      String cityName = input.getText().toString();
      if (!TextUtils.isEmpty(cityName)) getWeahterData(cityName);
      else Toast.makeText(getApplicationContext(),
                          getString(R.string.empty_city_name),
                          Toast.LENGTH_LONG).show();
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

  public void getWeahterData(final String cityName) {
    WeatherIntegrate weatherObj = dataBaseService.getWeatherData(cityName);
    if (!weatherObj.isEmpty() &&
            (weatherObj.getUpdatedDate() + OLD_DATE_LIMIT < ((new Date()).getTime()))) {
      WeatherStorage.setWeatherIntegrateSingleton(weatherObj);
      navDrawerListener.refreshFragments();
    } else loadWeatherData(cityName);
  }

  @OnClick(R.id.floating_action_btn)
  public void onClick(View v) {
    shareWeather();
  }

  public void shareWeather() {
    if (WeatherStorage.getWeatherIntegrateSingleton() != null) {
      Intent intent = new Intent(Intent.ACTION_SEND);
      intent.setType("text/plain");
      intent.putExtra(Intent.EXTRA_TEXT,
                      WeatherStorage
                              .getWeatherIntegrateSingleton()
                              .getAndFormatWeatherInfo());
      String title   = "Share";
      Intent chooser = Intent.createChooser(intent, title);
      startActivity(chooser);
    } else Toast.makeText(getApplicationContext(),
                          getString(R.string.empty_city),
                          Toast.LENGTH_LONG).show();
  }

  public void loadWeatherData(final String cityName) {
    CompletableFuture<WeatherModel> todaty = CompletableFuture.supplyAsync(() -> {
      try {
        return loadTodayWeather(cityName);
      } catch (IOException e) {
        e.printStackTrace();
        return new WeatherModel();
      }
    });
    CompletableFuture<WeatherWeekModel> week = CompletableFuture.supplyAsync(() -> {
      try {
        return loadWeekWeather(cityName);
      } catch (IOException e) {
        e.printStackTrace();
        return new WeatherWeekModel();
      }
    });
    CompletableFuture<WeatherIntegrate> res = todaty.thenCombine(week, WeatherIntegrate::new);

    try {
      WeatherIntegrate weather = res.get();
      if (!weather.isEmpty()) {
        WeatherStorage.setWeatherIntegrateSingleton(weather);
        dataBaseService.addOrUpdateWeather(weather);
        navDrawerListener.refreshFragments();
        saveCityNameToSharedPreferences(cityName);
      }
    } catch (InterruptedException | ExecutionException e) {
      e.printStackTrace();
    }
  }

  public WeatherModel loadTodayWeather(final String cityName) throws IOException {
    return App.getWeatherApi()
            .getWeatherByCityName(cityName, App.UNITS, App.APP_KEY)
            .execute().body();
  }

  public WeatherWeekModel loadWeekWeather(final String cityName) throws IOException {
    return App.getWeatherApi()
            .getWeatherWeekByCityName(cityName, App.UNITS, App.CNT, App.APP_KEY)
            .execute().body();
  }
}
