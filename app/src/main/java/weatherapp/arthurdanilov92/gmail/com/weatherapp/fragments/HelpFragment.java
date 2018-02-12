package weatherapp.arthurdanilov92.gmail.com.weatherapp.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import weatherapp.arthurdanilov92.gmail.com.weatherapp.R;

public class HelpFragment extends Fragment {

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
    View view = inflater.inflate(R.layout.help_frgt, container, false);
    return view;
  }
}
