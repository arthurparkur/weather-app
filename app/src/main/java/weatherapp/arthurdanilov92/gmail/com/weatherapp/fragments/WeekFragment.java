package weatherapp.arthurdanilov92.gmail.com.weatherapp.fragments;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
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

    day1Name.setText(weather.getDate(1));
    day1Temp.setText(weather.getTemp(1).toString());
    day1Icon.setImageDrawable(getIcon(weather.getIcon(1)));

    day2Name.setText(weather.getDate(2));
    day2Temp.setText(weather.getTemp(2).toString());
    day2Icon.setImageDrawable(getIcon(weather.getIcon(2)));

    day3Name.setText(weather.getDate(3));
    day3Temp.setText(weather.getTemp(3).toString());
    day3Icon.setImageDrawable(getIcon(weather.getIcon(3)));

    day4Name.setText(weather.getDate(4));
    day4Temp.setText(weather.getTemp(4).toString());
    day4Icon.setImageDrawable(getIcon(weather.getIcon(4)));

    day5Name.setText(weather.getDate(5));
    day5Temp.setText(weather.getTemp(5).toString());
    day5Icon.setImageDrawable(getIcon(weather.getIcon(5)));

    day6Name.setText(weather.getDate(6));
    day6Temp.setText(weather.getTemp(6).toString());
    day6Icon.setImageDrawable(getIcon(weather.getIcon(6)));

    day7Name.setText(weather.getDate(7));
    day7Temp.setText(weather.getTemp(7).toString());
    day7Icon.setImageDrawable(getIcon(weather.getIcon(7)));
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
