package weatherapp.arthurdanilov92.gmail.com.weatherapp;

import android.content.Context;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

class WeatherDataLoader {

  private static final String appKey               = "10792cf43e4992f76dfdb635ad0be4eb";
  private static final String OPEN_WEATHER_MAP_API = "http://api.openweathermap.org/data/2.5/weather?q=%s&appId=%s";
  private static final String STATUS_CODE          = "cod";
  private static final String NEW_LINE             = "\n";
  private static final int    SUCCESS_STATUS_CODE  = 200;

  static JSONObject getJSONData(Context context, String city) {
    try {
      URL               url        = new URL(String.format(OPEN_WEATHER_MAP_API, city, appKey));
      HttpURLConnection connection = (HttpURLConnection) url.openConnection();

      BufferedReader reader = new BufferedReader(
              new InputStreamReader(connection.getInputStream()));
      StringBuilder rawData = new StringBuilder(1024);
      String        tempVariable;
      while ((tempVariable = reader.readLine()) != null) {
        rawData.append(tempVariable).append(NEW_LINE);
      }
      reader.close();

      JSONObject jsonObject = new JSONObject(rawData.toString());
      if (jsonObject.getInt(STATUS_CODE) != SUCCESS_STATUS_CODE) {
        return null;
      }
      return jsonObject;
    } catch (Exception e) {
      e.printStackTrace();
      return null;
    }
  }
}
