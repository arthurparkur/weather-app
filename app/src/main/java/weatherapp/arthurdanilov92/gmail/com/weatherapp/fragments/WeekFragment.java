package weatherapp.arthurdanilov92.gmail.com.weatherapp.fragments;

import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import weatherapp.arthurdanilov92.gmail.com.weatherapp.R;
import weatherapp.arthurdanilov92.gmail.com.weatherapp.WeatherIntegrate;
import weatherapp.arthurdanilov92.gmail.com.weatherapp.WeatherStorage;

public class WeekFragment extends ContentFragment {
  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
    View view = inflater.inflate(R.layout.weather_week_frgt, container, false);

    LinearLayout     root       = view.findViewById(R.id.weather_root_fragment);
    WeatherIntegrate weatherObj = WeatherStorage.getWeatherIntegrateSingleton();

    if (weatherObj != null) {
      View content = inflater.inflate(R.layout.weather_week_frgt_content, null);
      root.addView(content);
      renderData(view, weatherObj);
    } else {
      renderEmptyView(root);
    }

    return view;
  }

  @Override
  public void renderData(View v, WeatherIntegrate weatherIntegrate) {

  }

  @Override
  public void renderEmptyView(ViewGroup root) {
    TextView chooseCityTitle = new TextView(getActivity());
    chooseCityTitle.setText(R.string.choose_city_text);
    chooseCityTitle.setLayoutParams(new ViewGroup.LayoutParams(
            ViewGroup.LayoutParams.WRAP_CONTENT,
            ViewGroup.LayoutParams.WRAP_CONTENT));
    chooseCityTitle.setGravity(Gravity.CENTER_HORIZONTAL);
    chooseCityTitle.setTextSize(30);
    root.addView(chooseCityTitle);
  }
}
