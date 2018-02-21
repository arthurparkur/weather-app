package weatherapp.arthurdanilov92.gmail.com.weatherapp.fragments;

import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import weatherapp.arthurdanilov92.gmail.com.weatherapp.R;
import weatherapp.arthurdanilov92.gmail.com.weatherapp.Utils;
import weatherapp.arthurdanilov92.gmail.com.weatherapp.WeatherStorage;
import weatherapp.arthurdanilov92.gmail.com.weatherapp.models.WeatherIntegrate;

public class TodayFragment extends ContentFragment {

  @Override
  public View onCreateView(LayoutInflater inflater,
                           ViewGroup container,
                           Bundle savedInstanceState) {
    View view = inflater.inflate(R.layout.weather_today_frgt, container, false);

    LinearLayout root = view.findViewById(R.id.today_root_fragment);
    WeatherIntegrate weatherObj = WeatherStorage.getWeatherIntegrateSingleton();

    if (weatherObj != null) {
      View content = inflater.inflate(R.layout.weather_today_frgt_content, null);
      root.addView(content);
      renderData(view, weatherObj);
    } else {
      renderEmptyView(root);
    }

    return view;
  }

  @Override
  public void renderData(View v, WeatherIntegrate obj) {
    TextView  cityName    = v.findViewById(R.id.city_name);
    ImageView icon        = v.findViewById(R.id.today_icon);
    TextView  temperature = v.findViewById(R.id.today_temperature);
    TextView  pressure    = v.findViewById(R.id.today_pressure);
    TextView  humidity    = v.findViewById(R.id.today_humidity);
    TextView  description = v.findViewById(R.id.today_description);

    cityName.setText(obj.getName());
    icon.setImageDrawable(Utils.getIcon(obj.getTodayIcon(), getActivity()));
    temperature.setText(obj.getTemperature().toString());
    pressure.setText(obj.getPressure().toString());
    humidity.setText(obj.getHumidity().toString());
    description.setText(obj.getDescription());
  }

  @Override
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
}
