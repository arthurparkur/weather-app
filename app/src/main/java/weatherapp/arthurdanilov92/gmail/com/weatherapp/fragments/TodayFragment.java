package weatherapp.arthurdanilov92.gmail.com.weatherapp.fragments;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import weatherapp.arthurdanilov92.gmail.com.weatherapp.R;
import weatherapp.arthurdanilov92.gmail.com.weatherapp.WeatherIntegrate;
import weatherapp.arthurdanilov92.gmail.com.weatherapp.WeatherStorage;

public class TodayFragment extends Fragment {

  @Override
  public View onCreateView(LayoutInflater inflater,
                           ViewGroup container,
                           Bundle savedInstanceState) {
    View view = inflater.inflate(R.layout.weather_today_frgt, container, false);

    LinearLayout root = view.findViewById(R.id.today_root_fragment);
    //WeatherModel     weatherObj = WeatherStorage.getWeatherObjSingleton();
    WeatherIntegrate weatherObj = WeatherStorage.getWeatherIntegrateSingleton();

    if (weatherObj != null) {
      View content = inflater.inflate(R.layout.today_weather_content, null);
      root.addView(content);
      renderData(view, weatherObj);
    } else {
      renderEmptyView(root);
    }

    return view;
  }

  public void renderData(View v, WeatherIntegrate obj) {
    TextView  cityName    = v.findViewById(R.id.city_name);
    ImageView icon        = v.findViewById(R.id.today_icon);
    TextView  temperature = v.findViewById(R.id.today_temperature);
    TextView  pressure    = v.findViewById(R.id.today_pressure);
    TextView  humidity    = v.findViewById(R.id.today_humidity);
    TextView  description = v.findViewById(R.id.today_description);

    cityName.setText(obj.getName());
    icon.setImageDrawable(getIcon(obj.getTodayIcon()));
    temperature.setText(obj.getTemperature().toString());
    pressure.setText(obj.getPressure().toString());
    humidity.setText(obj.getHumidity().toString());
    description.setText(obj.getDescription());
  }

  public void renderEmptyView(ViewGroup root) {
    TextView chooseCityTitle = new TextView(getActivity());
    chooseCityTitle.setText(R.string.choose_city_text);
    chooseCityTitle.setLayoutParams(new LayoutParams(
            LayoutParams.WRAP_CONTENT,
            LayoutParams.WRAP_CONTENT));
    chooseCityTitle.setGravity(Gravity.CENTER_HORIZONTAL);
    chooseCityTitle.setTextSize(30);
    root.addView(chooseCityTitle);
  }

  public Drawable getIcon(String icon) {
    switch (icon) {
      case "01d":
        return getResources().getDrawable(R.drawable.ic_01d);
      case "01n":
        return getResources().getDrawable(R.drawable.ic_01n);
      case "02d":
        return getResources().getDrawable(R.drawable.ic_02d);
      case "02n":
        return getResources().getDrawable(R.drawable.ic_02n);
      case "03d":
      case "03n":
      case "04n":
      case "04d":
        return getResources().getDrawable(R.drawable.ic_03d);
      case "09d":
      case "09n":
        return getResources().getDrawable(R.drawable.ic_09d);
      case "10d":
        return getResources().getDrawable(R.drawable.ic_10d);
      case "10n":
        return getResources().getDrawable(R.drawable.ic_10n);
      case "11d":
      case "11n":
        return getResources().getDrawable(R.drawable.ic_11d);
      case "13d":
      case "13n":
        return getResources().getDrawable(R.drawable.ic_13d);
      case "50d":
      case "50n":
        return getResources().getDrawable(R.drawable.ic_50d);
      default:
        return new ColorDrawable(Color.TRANSPARENT);
    }
  }

}
