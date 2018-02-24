package weatherapp.arthurdanilov92.gmail.com.weatherapp;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.support.v4.app.FragmentActivity;
import android.support.v4.content.ContextCompat;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class Utils {

  private final static String CELSIUS = "C \u00b0";

  public static String getDay(Integer dayCount) {
    Calendar c = Calendar.getInstance();
    c.add(Calendar.DATE, dayCount);

    SimpleDateFormat dayFormat = new SimpleDateFormat("E", Locale.US);
    return dayFormat.format(c.getTime());
  }

  public static String formatTemperature(Float temp) {
    return temp.toString() + " " + CELSIUS;
  }

  public static Drawable getIcon(String icon, FragmentActivity activity) {
    switch (icon) {
      case "01d":
        return ContextCompat.getDrawable(activity, R.drawable.ic_01d);
      case "01n":
        return ContextCompat.getDrawable(activity, R.drawable.ic_01n);
      case "02d":
        return ContextCompat.getDrawable(activity, R.drawable.ic_02d);
      case "02n":
        return ContextCompat.getDrawable(activity, R.drawable.ic_02n);
      case "03d":
      case "03n":
      case "04n":
      case "04d":
        return ContextCompat.getDrawable(activity, R.drawable.ic_03d);
      case "09d":
      case "09n":
        return ContextCompat.getDrawable(activity, R.drawable.ic_09d);
      case "10d":
        return ContextCompat.getDrawable(activity, R.drawable.ic_10d);
      case "10n":
        return ContextCompat.getDrawable(activity, R.drawable.ic_10n);
      case "11d":
      case "11n":
        return ContextCompat.getDrawable(activity, R.drawable.ic_11d);
      case "13d":
      case "13n":
        return ContextCompat.getDrawable(activity, R.drawable.ic_13d);
      case "50d":
      case "50n":
        return ContextCompat.getDrawable(activity, R.drawable.ic_50d);
      default:
        return new ColorDrawable(Color.TRANSPARENT);
    }
  }
}
