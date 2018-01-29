package weatherapp.arthurdanilov92.gmail.com.weatherapp.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.Date;

import weatherapp.arthurdanilov92.gmail.com.weatherapp.WeatherModel;

public class DataBaseService {

  private DataBaseHelper dbHelper;
  private SQLiteDatabase database;

  public DataBaseService(Context context) {
    dbHelper = new DataBaseHelper(context);
  }

  public void open() throws SQLException {
    database = dbHelper.getWritableDatabase();
  }

  public void close() {
    dbHelper.close();
  }


  public void addOrUpdateWeatherEntry(WeatherModel object) {
    ContentValues values = new ContentValues();
    values.put(DataBaseHelper.COL_CITY_NAME, object.getName());
    values.put(DataBaseHelper.COL_TEMPERATURE, object.getTemperature());
    values.put(DataBaseHelper.COL_PRESSURE, object.getPressure());
    values.put(DataBaseHelper.COL_HUMIDITY, object.getHumidity());
    values.put(DataBaseHelper.COL_DESCRIPTION, object.getDescription());
    //values.put(DataBaseHelper.COL_WEEK, object.);
    values.put(DataBaseHelper.COL_UPDATE_DATE, (new Date()).getTime());
    Log.d("tyyyyyyyyyyyyy", DataBaseHelper.COL_CITY_NAME + "=" + object.getName());

    int changedRow = database.update(DataBaseHelper.TABLE_NAME, values,
                                     DataBaseHelper.COL_CITY_NAME + "=" + object.getName(), null);
    /*if(changedRow == 0) {
      database.insertWithOnConflict(DataBaseHelper.TABLE_NAME,
                                    null,
                                    values,
                                    SQLiteDatabase.CONFLICT_REPLACE);
    }*/
  }

  public void editWeatherEntry(String cityName, Double temperature, Integer pressure,
                               Integer humidity, String description, String forWeek,
                               String updateDate) {
    ContentValues editWeather = new ContentValues();
    editWeather.put(DataBaseHelper.COL_CITY_NAME, cityName);
    if (temperature != null) editWeather.put(DataBaseHelper.COL_TEMPERATURE, temperature);
    if (pressure != null) editWeather.put(DataBaseHelper.COL_PRESSURE, pressure);
    if (humidity != null) editWeather.put(DataBaseHelper.COL_HUMIDITY, humidity);
    if (description != null) editWeather.put(DataBaseHelper.COL_DESCRIPTION, description);
    //if (forWeek != null)     editWeather.put(DataBaseHelper.COL_WEEK, forWeek);
    if (updateDate != null) editWeather.put(DataBaseHelper.COL_UPDATE_DATE, updateDate);

    database.update(DataBaseHelper.TABLE_NAME, editWeather,
                    DataBaseHelper.COL_CITY_NAME + "=" + cityName, null);
  }

  public void deleteWeatherEntry(String cityName) {
    database.delete(DataBaseHelper.TABLE_NAME, DataBaseHelper.COL_CITY_NAME
            + " = " + cityName, null);
  }

  public void deleteAll() {
    database.delete(DataBaseHelper.TABLE_NAME, null, null);
  }


}
