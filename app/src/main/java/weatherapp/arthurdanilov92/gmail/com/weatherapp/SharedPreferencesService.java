package weatherapp.arthurdanilov92.gmail.com.weatherapp;

import android.content.Context;
import android.content.SharedPreferences;

class SharedPreferencesService {

  private final String PREF_FILE = "WeatherPref";
  private final Context CONTEXT;

  SharedPreferencesService(Context context) {
    this.CONTEXT = context;
  }

  void saveCityNameToSharedPreferences(String city) {
    SharedPreferences        sp       = CONTEXT.getSharedPreferences(PREF_FILE, Context.MODE_PRIVATE);
    SharedPreferences.Editor spEditor = sp.edit();
    spEditor.putString(CONTEXT.getString(R.string.sp_city_key), city);
    spEditor.apply();
  }

  String loadCityNameFromSharedPreferences() {
    SharedPreferences sp = CONTEXT.getSharedPreferences(PREF_FILE, Context.MODE_PRIVATE);
    return sp.getString(CONTEXT.getString(R.string.sp_city_key), "");
  }

}
