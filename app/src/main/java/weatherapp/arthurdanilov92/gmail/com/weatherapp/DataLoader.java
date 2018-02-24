package weatherapp.arthurdanilov92.gmail.com.weatherapp;

import java.io.IOException;

import weatherapp.arthurdanilov92.gmail.com.weatherapp.models.WeatherModel;
import weatherapp.arthurdanilov92.gmail.com.weatherapp.models.WeatherWeekModel;

class DataLoader {

  private static DataLoader dataLoader = null;
  private final  String     UNITS      = "metric";
  private final  Integer    DAYS_COUNT = 7;

  private DataLoader() {
  }

  static DataLoader getDataLoader() {
    if (dataLoader == null) return dataLoader = new DataLoader();
    return dataLoader;
  }

  WeatherModel loadTodayWeather(final String cityName) throws IOException {
    return App.getWeatherApi()
            .getWeatherByCityName(cityName, UNITS, App.APP_KEY)
            .execute().body();
  }

  WeatherWeekModel loadWeekWeather(final String cityName) throws IOException {
    return App.getWeatherApi()
            .getWeatherWeekByCityName(cityName, UNITS, DAYS_COUNT, App.APP_KEY)
            .execute().body();
  }
}
