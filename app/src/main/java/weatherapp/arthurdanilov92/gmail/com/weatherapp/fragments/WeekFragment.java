package weatherapp.arthurdanilov92.gmail.com.weatherapp.fragments;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import weatherapp.arthurdanilov92.gmail.com.weatherapp.R;
import weatherapp.arthurdanilov92.gmail.com.weatherapp.Utils;
import weatherapp.arthurdanilov92.gmail.com.weatherapp.WeatherSingleton;
import weatherapp.arthurdanilov92.gmail.com.weatherapp.models.WeatherIntegrate;

public class WeekFragment extends ContentFragment {

  @Override
  public View onCreateView(LayoutInflater inflater,
                           ViewGroup container,
                           Bundle savedInstanceState) {
    View view = inflater.inflate(R.layout.weather_week_frgt, container, false);

    LinearLayout     root       = view.findViewById(R.id.weather_root_fragment);
    WeatherIntegrate weatherObj = WeatherSingleton.getWeatherIntegrateSingleton();

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
  public void renderData(View v, WeatherIntegrate weather) {
    TextView  cityName = v.findViewById(R.id.city_name);
    TextView  day1Name = v.findViewById(R.id.day1_name);
    TextView  day2Name = v.findViewById(R.id.day2_name);
    TextView  day3Name = v.findViewById(R.id.day3_name);
    TextView  day4Name = v.findViewById(R.id.day4_name);
    TextView  day5Name = v.findViewById(R.id.day5_name);
    TextView  day6Name = v.findViewById(R.id.day6_name);
    TextView  day7Name = v.findViewById(R.id.day7_name);
    TextView  day1Temp = v.findViewById(R.id.day1_temp);
    TextView  day2Temp = v.findViewById(R.id.day2_temp);
    TextView  day3Temp = v.findViewById(R.id.day3_temp);
    TextView  day4Temp = v.findViewById(R.id.day4_temp);
    TextView  day5Temp = v.findViewById(R.id.day5_temp);
    TextView  day6Temp = v.findViewById(R.id.day6_temp);
    TextView  day7Temp = v.findViewById(R.id.day7_temp);
    ImageView day1Icon = v.findViewById(R.id.day1_icon);
    ImageView day2Icon = v.findViewById(R.id.day2_icon);
    ImageView day3Icon = v.findViewById(R.id.day3_icon);
    ImageView day4Icon = v.findViewById(R.id.day4_icon);
    ImageView day5Icon = v.findViewById(R.id.day5_icon);
    ImageView day6Icon = v.findViewById(R.id.day6_icon);
    ImageView day7Icon = v.findViewById(R.id.day7_icon);

    cityName.setText(weather.getName());

    FragmentActivity activity = getActivity();

    day1Name.setText(Utils.getDay(0));
    day1Temp.setText(Utils.formatTemperature(weather.getTemp(1)));
    day1Icon.setImageDrawable(Utils.getIcon(weather.getIcon(1), activity));

    day2Name.setText(Utils.getDay(1));
    day2Temp.setText(Utils.formatTemperature(weather.getTemp(2)));
    day2Icon.setImageDrawable(Utils.getIcon(weather.getIcon(2), activity));

    day3Name.setText(Utils.getDay(2));
    day3Temp.setText(Utils.formatTemperature(weather.getTemp(3)));
    day3Icon.setImageDrawable(Utils.getIcon(weather.getIcon(3), activity));

    day4Name.setText(Utils.getDay(3));
    day4Temp.setText(Utils.formatTemperature(weather.getTemp(4)));
    day4Icon.setImageDrawable(Utils.getIcon(weather.getIcon(4), activity));

    day5Name.setText(Utils.getDay(4));
    day5Temp.setText(Utils.formatTemperature(weather.getTemp(5)));
    day5Icon.setImageDrawable(Utils.getIcon(weather.getIcon(5), activity));

    day6Name.setText(Utils.getDay(5));
    day6Temp.setText(Utils.formatTemperature(weather.getTemp(6)));
    day6Icon.setImageDrawable(Utils.getIcon(weather.getIcon(6), activity));

    day7Name.setText(Utils.getDay(6));
    day7Temp.setText(Utils.formatTemperature(weather.getTemp(7)));
    day7Icon.setImageDrawable(Utils.getIcon(weather.getIcon(7), activity));
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
