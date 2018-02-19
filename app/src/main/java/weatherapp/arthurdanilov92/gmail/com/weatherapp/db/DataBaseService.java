package weatherapp.arthurdanilov92.gmail.com.weatherapp.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import java.util.Date;

import weatherapp.arthurdanilov92.gmail.com.weatherapp.WeatherIntegrate;
import weatherapp.arthurdanilov92.gmail.com.weatherapp.models.WeatherModel;
import weatherapp.arthurdanilov92.gmail.com.weatherapp.models.WeatherWeekModel;

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
    values.put(DataBaseHelper.COL_ICON, object.getIcon());
    values.put(DataBaseHelper.COL_UPDATE_DATE, (new Date()).getTime());

    int changedRow = database.update(DataBaseHelper.TODAY_TABLE_NAME, values,
                                     DataBaseHelper.COL_CITY_NAME + "=\""
                                             + object.getName() + "\"", null);
    if (changedRow == 0) {
      database.insertWithOnConflict(DataBaseHelper.TODAY_TABLE_NAME,
                                    null,
                                    values,
                                    SQLiteDatabase.CONFLICT_REPLACE);
    }
  }

  public void addOrUpdateWeatherWeekEntry(WeatherWeekModel object) {
    ContentValues values = new ContentValues();
    values.put(DataBaseHelper.COL_CITY_NAME, object.getName());
    values.put(DataBaseHelper.COL_DAY1_TEMP, object.getTemp(1));
    values.put(DataBaseHelper.COL_DAY1_ICO, object.getIcon(1));
    values.put(DataBaseHelper.COL_DAY2_TEMP, object.getTemp(2));
    values.put(DataBaseHelper.COL_DAY2_ICO, object.getIcon(2));
    values.put(DataBaseHelper.COL_DAY3_TEMP, object.getTemp(3));
    values.put(DataBaseHelper.COL_DAY3_ICO, object.getIcon(3));
    values.put(DataBaseHelper.COL_DAY4_TEMP, object.getTemp(4));
    values.put(DataBaseHelper.COL_DAY4_ICO, object.getIcon(4));
    values.put(DataBaseHelper.COL_DAY5_TEMP, object.getTemp(5));
    values.put(DataBaseHelper.COL_DAY5_ICO, object.getIcon(5));
    values.put(DataBaseHelper.COL_DAY6_TEMP, object.getTemp(6));
    values.put(DataBaseHelper.COL_DAY6_ICO, object.getIcon(6));
    values.put(DataBaseHelper.COL_DAY7_TEMP, object.getTemp(7));
    values.put(DataBaseHelper.COL_DAY7_ICO, object.getIcon(7));
    values.put(DataBaseHelper.COL_UPDATE_DATE, (new Date()).getTime());

    int changedRow = database.update(DataBaseHelper.WEEK_TABLE_NAME, values,
                                     DataBaseHelper.COL_CITY_NAME + "=\""
                                             + object.getName() + "\"", null);
    if (changedRow == 0) {
      database.insertWithOnConflict(DataBaseHelper.WEEK_TABLE_NAME,
                                    null,
                                    values,
                                    SQLiteDatabase.CONFLICT_REPLACE);
    }
  }

  public void newAddOrUpdateWeather(WeatherIntegrate object) {
    ContentValues values = new ContentValues();
    values.put(DataBaseHelper.COL_CITY_NAME, object.getName());
    values.put(DataBaseHelper.COL_TEMPERATURE, object.getTemperature());
    values.put(DataBaseHelper.COL_PRESSURE, object.getPressure());
    values.put(DataBaseHelper.COL_HUMIDITY, object.getHumidity());
    values.put(DataBaseHelper.COL_DESCRIPTION, object.getDescription());
    values.put(DataBaseHelper.COL_ICON, object.getTodayIcon());
    values.put(DataBaseHelper.COL_DAY1_TEMP, object.getTemp(1));
    values.put(DataBaseHelper.COL_DAY1_ICO, object.getIcon(1));
    values.put(DataBaseHelper.COL_DAY2_TEMP, object.getTemp(2));
    values.put(DataBaseHelper.COL_DAY2_ICO, object.getIcon(2));
    values.put(DataBaseHelper.COL_DAY3_TEMP, object.getTemp(3));
    values.put(DataBaseHelper.COL_DAY3_ICO, object.getIcon(3));
    values.put(DataBaseHelper.COL_DAY4_TEMP, object.getTemp(4));
    values.put(DataBaseHelper.COL_DAY4_ICO, object.getIcon(4));
    values.put(DataBaseHelper.COL_DAY5_TEMP, object.getTemp(5));
    values.put(DataBaseHelper.COL_DAY5_ICO, object.getIcon(5));
    values.put(DataBaseHelper.COL_DAY6_TEMP, object.getTemp(6));
    values.put(DataBaseHelper.COL_DAY6_ICO, object.getIcon(6));
    values.put(DataBaseHelper.COL_DAY7_TEMP, object.getTemp(7));
    values.put(DataBaseHelper.COL_DAY7_ICO, object.getIcon(7));
    values.put(DataBaseHelper.COL_UPDATE_DATE, (new Date()).getTime());

    int changedRow = database.update(DataBaseHelper.TODAY_TABLE_NAME, values,
                                     DataBaseHelper.COL_CITY_NAME + "=\""
                                             + object.getName() + "\"", null);
    if (changedRow == 0) {
      database.insertWithOnConflict(DataBaseHelper.TODAY_TABLE_NAME,
                                    null,
                                    values,
                                    SQLiteDatabase.CONFLICT_REPLACE);
    }
  }

  public WeatherIntegrate newGetWeatherData(String cityName) {
    Cursor           cr         = null;
    WeatherIntegrate weatherObj = null;
    try {
      String sql = "SELECT * FROM " + DataBaseHelper.TODAY_TABLE_NAME +
              " WHERE " + DataBaseHelper.COL_CITY_NAME + " = ? COLLATE NOCASE";
      cr = database.rawQuery(sql, new String[]{cityName});
      if (cr.getCount() == 0) {
        return new WeatherIntegrate();
      }
      if (cr.getCount() > 0) {
        cr.moveToFirst();
        weatherObj = new WeatherIntegrate();
        weatherObj.setName(cr.getString(cr.getColumnIndexOrThrow(DataBaseHelper.COL_CITY_NAME)));
        weatherObj.setHumidity(Integer.parseInt(cr.getString(cr.getColumnIndexOrThrow(DataBaseHelper.COL_HUMIDITY))));
        weatherObj.setPressure(Integer.parseInt(cr.getString(cr.getColumnIndexOrThrow(DataBaseHelper.COL_PRESSURE))));
        weatherObj.setTemp(Double.parseDouble(cr.getString(cr.getColumnIndexOrThrow(DataBaseHelper.COL_PRESSURE))));
        weatherObj.setDescription(cr.getString(cr.getColumnIndexOrThrow(DataBaseHelper.COL_PRESSURE)));
        weatherObj.setTodayIcon(cr.getString(cr.getColumnIndexOrThrow(DataBaseHelper.COL_ICON)));

        weatherObj.setTemp(Float.parseFloat(cr.getString(cr.getColumnIndexOrThrow(DataBaseHelper.COL_DAY1_TEMP))));
        weatherObj.setTemp(Float.parseFloat(cr.getString(cr.getColumnIndexOrThrow(DataBaseHelper.COL_DAY2_TEMP))));
        weatherObj.setTemp(Float.parseFloat(cr.getString(cr.getColumnIndexOrThrow(DataBaseHelper.COL_DAY3_TEMP))));
        weatherObj.setTemp(Float.parseFloat(cr.getString(cr.getColumnIndexOrThrow(DataBaseHelper.COL_DAY4_TEMP))));
        weatherObj.setTemp(Float.parseFloat(cr.getString(cr.getColumnIndexOrThrow(DataBaseHelper.COL_DAY5_TEMP))));
        weatherObj.setTemp(Float.parseFloat(cr.getString(cr.getColumnIndexOrThrow(DataBaseHelper.COL_DAY6_TEMP))));
        weatherObj.setTemp(Float.parseFloat(cr.getString(cr.getColumnIndexOrThrow(DataBaseHelper.COL_DAY7_TEMP))));
        weatherObj.setIcon(1, cr.getString(cr.getColumnIndexOrThrow(DataBaseHelper.COL_DAY1_ICO)));
        weatherObj.setIcon(2, cr.getString(cr.getColumnIndexOrThrow(DataBaseHelper.COL_DAY2_ICO)));
        weatherObj.setIcon(3, cr.getString(cr.getColumnIndexOrThrow(DataBaseHelper.COL_DAY3_ICO)));
        weatherObj.setIcon(4, cr.getString(cr.getColumnIndexOrThrow(DataBaseHelper.COL_DAY4_ICO)));
        weatherObj.setIcon(5, cr.getString(cr.getColumnIndexOrThrow(DataBaseHelper.COL_DAY5_ICO)));
        weatherObj.setIcon(6, cr.getString(cr.getColumnIndexOrThrow(DataBaseHelper.COL_DAY6_ICO)));
        weatherObj.setIcon(7, cr.getString(cr.getColumnIndexOrThrow(DataBaseHelper.COL_DAY7_ICO)));
        weatherObj.setUpdatedDate(Long.parseLong(cr.getString(cr.getColumnIndexOrThrow(DataBaseHelper.COL_UPDATE_DATE))));
      }
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      if (cr != null) cr.close();
    }
    return weatherObj;
  }

  public WeatherModel getWeatherData(String cityName) {
    Cursor       cr         = null;
    WeatherModel weatherObj = null;
    try {
      String sql = "SELECT * FROM " + DataBaseHelper.TODAY_TABLE_NAME +
              " WHERE " + DataBaseHelper.COL_CITY_NAME + " = ? COLLATE NOCASE";
      cr = database.rawQuery(sql, new String[]{cityName});
      if (cr.getCount() == 0) {
        return weatherObj;
      }
      if (cr.getCount() > 0) {
        cr.moveToFirst();
        weatherObj = new WeatherModel();
        weatherObj.setName(cr.getString(cr.getColumnIndexOrThrow(DataBaseHelper.COL_CITY_NAME)));
        weatherObj.setHumidity(Integer.parseInt(cr.getString(cr.getColumnIndexOrThrow(DataBaseHelper.COL_HUMIDITY))));
        weatherObj.setPressure(Integer.parseInt(cr.getString(cr.getColumnIndexOrThrow(DataBaseHelper.COL_PRESSURE))));
        weatherObj.setTemp(Double.parseDouble(cr.getString(cr.getColumnIndexOrThrow(DataBaseHelper.COL_PRESSURE))));
        weatherObj.setDescription(cr.getString(cr.getColumnIndexOrThrow(DataBaseHelper.COL_PRESSURE)));
        weatherObj.setIcon(cr.getString(cr.getColumnIndexOrThrow(DataBaseHelper.COL_ICON)));
        weatherObj.setUpdatedDate(Long.parseLong(cr.getString(cr.getColumnIndexOrThrow(DataBaseHelper.COL_UPDATE_DATE))));
      }
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      if (cr != null) cr.close();
    }
    return weatherObj;
  }

  public WeatherWeekModel getWeatherWeekData(String cityName) {
    Cursor           cr         = null;
    WeatherWeekModel weatherObj = null;
    try {
      String sql = "SELECT * FROM " + DataBaseHelper.WEEK_TABLE_NAME +
              " WHERE " + DataBaseHelper.COL_CITY_NAME + " = ? COLLATE NOCASE";
      cr = database.rawQuery(sql, new String[]{cityName});
      if (cr.getCount() == 0) {
        return weatherObj;
      }
      if (cr.getCount() > 0) {
        cr.moveToFirst();
        weatherObj = new WeatherWeekModel();
        weatherObj.setName(cr.getString(cr.getColumnIndexOrThrow(DataBaseHelper.COL_CITY_NAME)));
        weatherObj.setTemp(Float.parseFloat(cr.getString(cr.getColumnIndexOrThrow(DataBaseHelper.COL_DAY1_TEMP))));
        weatherObj.setTemp(Float.parseFloat(cr.getString(cr.getColumnIndexOrThrow(DataBaseHelper.COL_DAY2_TEMP))));
        weatherObj.setTemp(Float.parseFloat(cr.getString(cr.getColumnIndexOrThrow(DataBaseHelper.COL_DAY3_TEMP))));
        weatherObj.setTemp(Float.parseFloat(cr.getString(cr.getColumnIndexOrThrow(DataBaseHelper.COL_DAY4_TEMP))));
        weatherObj.setTemp(Float.parseFloat(cr.getString(cr.getColumnIndexOrThrow(DataBaseHelper.COL_DAY5_TEMP))));
        weatherObj.setTemp(Float.parseFloat(cr.getString(cr.getColumnIndexOrThrow(DataBaseHelper.COL_DAY6_TEMP))));
        weatherObj.setTemp(Float.parseFloat(cr.getString(cr.getColumnIndexOrThrow(DataBaseHelper.COL_DAY7_TEMP))));
        weatherObj.setIcon(1, cr.getString(cr.getColumnIndexOrThrow(DataBaseHelper.COL_DAY1_ICO)));
        weatherObj.setIcon(2, cr.getString(cr.getColumnIndexOrThrow(DataBaseHelper.COL_DAY2_ICO)));
        weatherObj.setIcon(3, cr.getString(cr.getColumnIndexOrThrow(DataBaseHelper.COL_DAY3_ICO)));
        weatherObj.setIcon(4, cr.getString(cr.getColumnIndexOrThrow(DataBaseHelper.COL_DAY4_ICO)));
        weatherObj.setIcon(5, cr.getString(cr.getColumnIndexOrThrow(DataBaseHelper.COL_DAY5_ICO)));
        weatherObj.setIcon(6, cr.getString(cr.getColumnIndexOrThrow(DataBaseHelper.COL_DAY6_ICO)));
        weatherObj.setIcon(7, cr.getString(cr.getColumnIndexOrThrow(DataBaseHelper.COL_DAY7_ICO)));
        weatherObj.setUpdatedDate(Long.parseLong(cr.getString(cr.getColumnIndexOrThrow(DataBaseHelper.COL_UPDATE_DATE))));
      }
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      if (cr != null) cr.close();
    }
    return weatherObj;
  }

  public void deleteWeatherEntry(String cityName) {
    database.delete(DataBaseHelper.TODAY_TABLE_NAME, DataBaseHelper.COL_CITY_NAME
            + " = " + cityName, null);
  }

  public void deleteAll() {
    database.delete(DataBaseHelper.TODAY_TABLE_NAME, null, null);
  }


}
