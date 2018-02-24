package weatherapp.arthurdanilov92.gmail.com.weatherapp.models;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class WeatherWeekItem {
  Long                 dt;
  @SerializedName("weather")
  List<WeatherElement> weatherElements;
  @SerializedName("temp")
  TemperatureElement   temperatureElement;

  public WeatherWeekItem() {
    this.weatherElements = new ArrayList<WeatherElement>();
    weatherElements.add(new WeatherElement());
    this.temperatureElement = new TemperatureElement();
  }

  public List<WeatherElement> getWeatherElements() {
    return weatherElements;
  }

  public TemperatureElement getTemperatureElement() {
    return temperatureElement;
  }

  public String getDt() {
    return dt.toString();
  }

  public void setDt(String dt) {
    this.dt = Long.parseLong(dt);
  }
}