package weatherapp.arthurdanilov92.gmail.com.weatherapp.models;

import java.util.ArrayList;
import java.util.List;

public class WeatherModel {

  String name;
  MainInfo main;
  List<WeatherInfo> weather;

  public WeatherModel() {
    this.main = new MainInfo();
    this.weather = new ArrayList<>();
  }

  public MainInfo getMain() {
    return main;
  }

  public List<WeatherInfo> getWeather() {
    return weather;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getIcon() {
    return weather.get(0).icon;
  }

  public void setIcon(String icon) {
    int         l  = weather.size();
    WeatherInfo el = new WeatherInfo();
    el.icon = icon;
    weather.add(l, el);
  }
}
