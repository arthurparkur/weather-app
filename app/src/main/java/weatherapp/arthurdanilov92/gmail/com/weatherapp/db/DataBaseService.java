package weatherapp.arthurdanilov92.gmail.com.weatherapp.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

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
    values.put(DataBaseHelper.COL_UPDATE_DATE, (new Date()).getTime());

    int changedRow = database.update(DataBaseHelper.TABLE_NAME, values,
                                     DataBaseHelper.COL_CITY_NAME + "=\""
                                             + object.getName() + "\"", null);
    if (changedRow == 0) {
      database.insertWithOnConflict(DataBaseHelper.TABLE_NAME,
                                    null,
                                    values,
                                    SQLiteDatabase.CONFLICT_REPLACE);
    }
  }

  public WeatherModel getWeatherData(String cityName) {
    Cursor       cr         = null;
    WeatherModel weatherObj = null;
    try {
      String sql = "SELECT * FROM " + DataBaseHelper.TABLE_NAME +
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
    database.delete(DataBaseHelper.TABLE_NAME, DataBaseHelper.COL_CITY_NAME
            + " = " + cityName, null);
  }

  public void deleteAll() {
    database.delete(DataBaseHelper.TABLE_NAME, null, null);
  }


}
