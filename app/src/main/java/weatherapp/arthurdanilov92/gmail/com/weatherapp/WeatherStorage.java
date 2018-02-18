package weatherapp.arthurdanilov92.gmail.com.weatherapp;

import weatherapp.arthurdanilov92.gmail.com.weatherapp.models.WeatherModel;
import weatherapp.arthurdanilov92.gmail.com.weatherapp.models.WeatherWeekModel;

public class WeatherStorage {

  private static WeatherModel     weatherObjSingleton     = null;
  private static WeatherWeekModel weatherWeekObjSingleton = null;

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
}
