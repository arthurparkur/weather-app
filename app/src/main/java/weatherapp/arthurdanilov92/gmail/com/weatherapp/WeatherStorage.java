package weatherapp.arthurdanilov92.gmail.com.weatherapp;

import weatherapp.arthurdanilov92.gmail.com.weatherapp.models.WeatherModel;
import weatherapp.arthurdanilov92.gmail.com.weatherapp.models.WeatherWeekModel;

public class WeatherStorage {

  private static WeatherModel     weatherObjSingleton       = null;
  private static WeatherWeekModel weatherWeekObjSingleton   = null;
  private static WeatherIntegrate weatherIntegrateSingleton = null;

  public static WeatherModel getWeatherObjSingleton() {
    return weatherObjSingleton;
  }

  public static void setWeatherObjSingleton(WeatherModel weatherObj) {
    weatherObjSingleton = weatherObj;
  }

  public static WeatherWeekModel getWeatherWeekObjSingleton() {
    return weatherWeekObjSingleton;
  }

  public static void setWeatherWeekObjSingleton(WeatherWeekModel weatherWeekObjSingleton) {
    WeatherStorage.weatherWeekObjSingleton = weatherWeekObjSingleton;
  }

  public static WeatherIntegrate getWeatherIntegrateSingleton() {
    return weatherIntegrateSingleton;
  }

  public static void setWeatherIntegrateSingleton(WeatherIntegrate weatherIntegrateSingleton) {
    WeatherStorage.weatherIntegrateSingleton = weatherIntegrateSingleton;
  }
}
