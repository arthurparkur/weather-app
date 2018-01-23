package weatherapp.arthurdanilov92.gmail.com.weatherapp;

import android.content.Context;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

class WeatherDataLoader {

  private static final String cityId               = "509820";
  private static final String appId                = "10792cf43e4992f76dfdb635ad0be4eb";
  private static final String OPEN_WEATHER_MAP_API = "http://api.openweathermap.org/data/2.5/forecast?id=%s&appid=%s";
  private static final String KEY                  = "x-api-key";
  private static final String RESPONSE             = "cod";
  private static final String NEW_LINE             = "\n";
  private static final int    ALL_GOOD             = 200;

  static JSONObject getJSONData(Context context, String city) {
    try {
      URL               url        = new URL(String.format(OPEN_WEATHER_MAP_API, cityId, appId));
      HttpURLConnection connection = (HttpURLConnection) url.openConnection();
      connection.addRequestProperty("id", cityId);
      connection.addRequestProperty("appid", appId);

      BufferedReader reader  = new BufferedReader(new InputStreamReader(connection.getInputStream()));
      StringBuilder  rawData = new StringBuilder(1024);
      String         tempVariable;
      while ((tempVariable = reader.readLine()) != null) {
        rawData.append(tempVariable).append(NEW_LINE);
      }
      reader.close();

      JSONObject jsonObject = new JSONObject(rawData.toString());
      if (jsonObject.getInt(RESPONSE) != ALL_GOOD) {
        return null;
      }
      return jsonObject;
    } catch (Exception e) {
      return null;
    }
  }
}
