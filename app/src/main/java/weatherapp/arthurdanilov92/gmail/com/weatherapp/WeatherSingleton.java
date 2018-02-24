package weatherapp.arthurdanilov92.gmail.com.weatherapp;

import weatherapp.arthurdanilov92.gmail.com.weatherapp.models.WeatherIntegrate;

public class WeatherSingleton {

  private static WeatherIntegrate weatherIntegrateSingleton = null;

  public static WeatherIntegrate getWeatherIntegrateSingleton() {
    return weatherIntegrateSingleton;
  }

  static void setWeatherIntegrateSingleton(WeatherIntegrate weatherIntegrateSingleton) {
    WeatherSingleton.weatherIntegrateSingleton = weatherIntegrateSingleton;
  }

}
