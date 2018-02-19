package weatherapp.arthurdanilov92.gmail.com.weatherapp.fragments;

import android.support.v4.app.Fragment;
import android.view.View;
import android.view.ViewGroup;

import weatherapp.arthurdanilov92.gmail.com.weatherapp.WeatherIntegrate;

abstract class ContentFragment extends Fragment {

  public abstract void renderData(View v, WeatherIntegrate weatherIntegrate);

  public abstract void renderEmptyView(ViewGroup viewGroup);
}
