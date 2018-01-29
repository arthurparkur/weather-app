package weatherapp.arthurdanilov92.gmail.com.weatherapp.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DataBaseHelper extends SQLiteOpenHelper {
  public static final  String TABLE_NAME      = "weather_history";
  //columns
  public static final  String COL_ID          = "id";
  public static final  String COL_CITY_NAME   = "city_name";
  public static final  String COL_TEMPERATURE = "temperature";
  public static final  String COL_PRESSURE    = "pressure";
  public static final  String COL_HUMIDITY    = "humidity";
  public static final  String COL_DESCRIPTION = "description";
  public static final  String COL_WEEK        = "week_weather";
  public static final  String COL_UPDATE_DATE = "update_date";
  private static final String DB_NAME         = "weatherApp.db";
  private static final int    DB_V            = 1;


  public DataBaseHelper(Context context) {
    super(context, DB_NAME, null, DB_V);
  }

  @Override
  public void onCreate(SQLiteDatabase db) {
    db.execSQL("CREATE TABLE " + TABLE_NAME + " (" + COL_ID
                       + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                       + COL_CITY_NAME + " TEXT UNIQUE,"
                       + COL_TEMPERATURE + " REAL,"
                       + COL_PRESSURE + " INTEGER,"
                       + COL_HUMIDITY + " INTEGER,"
                       + COL_DESCRIPTION + " TEXT,"
                       //+ COL_WEEK + " TEXT,"
                       + COL_UPDATE_DATE + " TEXT);");
  }

  @Override
  public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
    onCreate(db);

  }
}
