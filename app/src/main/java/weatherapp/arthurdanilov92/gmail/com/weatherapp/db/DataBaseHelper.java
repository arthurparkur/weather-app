package weatherapp.arthurdanilov92.gmail.com.weatherapp.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DataBaseHelper extends SQLiteOpenHelper {

  public static final  String TODAY_TABLE_NAME = "weather_history";
  public static final  String WEEK_TABLE_NAME  = "week_weather_history";
  //columns
  public static final  String COL_ID           = "id";
  public static final  String COL_CITY_NAME    = "city_name";
  public static final  String COL_TEMPERATURE  = "temperature";
  public static final  String COL_PRESSURE     = "pressure";
  public static final  String COL_HUMIDITY     = "humidity";
  public static final  String COL_DESCRIPTION  = "description";
  public static final  String COL_UPDATE_DATE  = "update_date";
  public static final  String COL_DAY1_TEMP    = "day1_temp";
  public static final  String COL_DAY1_ICO     = "day1_icon";
  public static final  String COL_DAY2_TEMP    = "day2_temp";
  public static final  String COL_DAY2_ICO     = "day2_icon";
  public static final  String COL_DAY3_TEMP    = "day3_temp";
  public static final  String COL_DAY3_ICO     = "day3_icon";
  public static final  String COL_DAY4_TEMP    = "day4_temp";
  public static final  String COL_DAY4_ICO     = "day4_icon";
  public static final  String COL_DAY5_TEMP    = "day5_temp";
  public static final  String COL_DAY5_ICO     = "day5_icon";
  public static final  String COL_DAY6_TEMP    = "day6_temp";
  public static final  String COL_DAY6_ICO     = "day6_icon";
  public static final  String COL_DAY7_TEMP    = "day7_temp";
  public static final  String COL_DAY7_ICO     = "day7_icon";
  private static final String DB_NAME          = "weatherApp.db";
  private static final int    DB_V             = 1;


  public DataBaseHelper(Context context) {
    super(context, DB_NAME, null, DB_V);
  }

  @Override
  public void onCreate(SQLiteDatabase db) {
    db.execSQL("CREATE TABLE " + TODAY_TABLE_NAME + " (" + COL_ID
                       + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                       + COL_CITY_NAME + " TEXT UNIQUE,"
                       + COL_TEMPERATURE + " REAL,"
                       + COL_PRESSURE + " INTEGER,"
                       + COL_HUMIDITY + " INTEGER,"
                       + COL_DESCRIPTION + " TEXT,"
                       + COL_UPDATE_DATE + " TEXT);");

    db.execSQL("CREATE TABLE " + WEEK_TABLE_NAME + " (" + COL_ID
                       + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                       + COL_CITY_NAME + " TEXT UNIQUE,"
                       + COL_DAY1_TEMP + " REAL,"
                       + COL_DAY1_ICO + " TEXT,"
                       + COL_DAY2_TEMP + " REAL,"
                       + COL_DAY2_ICO + " TEXT,"
                       + COL_DAY3_TEMP + " REAL,"
                       + COL_DAY3_ICO + " TEXT,"
                       + COL_DAY4_TEMP + " REAL,"
                       + COL_DAY4_ICO + " TEXT,"
                       + COL_DAY5_TEMP + " REAL,"
                       + COL_DAY5_ICO + " TEXT,"
                       + COL_DAY6_TEMP + " REAL,"
                       + COL_DAY6_ICO + " TEXT,"
                       + COL_DAY7_TEMP + " REAL,"
                       + COL_DAY7_ICO + " TEXT,"
                       + COL_UPDATE_DATE + " TEXT);");
  }

  @Override
  public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    Log.d("onUpgrade", "onUpgrade");
    onCreate(db);

  }
}
