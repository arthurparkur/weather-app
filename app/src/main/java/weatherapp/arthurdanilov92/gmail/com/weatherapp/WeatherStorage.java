package weatherapp.arthurdanilov92.gmail.com.weatherapp;

import weatherapp.arthurdanilov92.gmail.com.weatherapp.models.WeatherIntegrate;

public class WeatherStorage {

  private static WeatherIntegrate weatherIntegrateSingleton = null;

  public static WeatherIntegrate getWeatherIntegrateSingleton() {
    return weatherIntegrateSingleton;
  }

  public static void setWeatherIntegrateSingleton(WeatherIntegrate weatherIntegrateSingleton) {
    WeatherStorage.weatherIntegrateSingleton = weatherIntegrateSingleton;
  }

}
