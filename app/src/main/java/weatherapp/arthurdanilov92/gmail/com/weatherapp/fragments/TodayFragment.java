package weatherapp.arthurdanilov92.gmail.com.weatherapp.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import weatherapp.arthurdanilov92.gmail.com.weatherapp.R;
import weatherapp.arthurdanilov92.gmail.com.weatherapp.WeatherModel;
import weatherapp.arthurdanilov92.gmail.com.weatherapp.WeatherStorage;

public class TodayFragment extends Fragment {

  @Override
  public View onCreateView(LayoutInflater inflater,
                           ViewGroup container,
                           Bundle savedInstanceState) {
    View view = inflater.inflate(R.layout.weather_today_frgt, container, false);

    System.out.println("--------------------------------------------------------------");

    WeatherModel weatherObj = WeatherStorage.getWeatherObjSingleton();

    if (weatherObj != null) {
      container.removeAllViews();
      View content = inflater.inflate(R.layout.today_weather_content, null);
      container.addView(content);
    } else System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++++");

    return view;
  }
}
