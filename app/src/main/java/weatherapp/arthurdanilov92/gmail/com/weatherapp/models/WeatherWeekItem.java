package weatherapp.arthurdanilov92.gmail.com.weatherapp.models;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class WeatherWeekItem {
  @SerializedName("clouds")
  Integer              clouds;
  @SerializedName("deg")
  Integer              deg;
  @SerializedName("dt")
  Float                dt;
  @SerializedName("humidity")
  Integer              humidity;
  @SerializedName("pressure")
  Float                pressure;
  @SerializedName("snow")
  Float                snow;
  @SerializedName("speed")
  Float                speed;
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

  public void setWeatherElements(List<WeatherElement> weatherElements) {
    this.weatherElements = weatherElements;
  }

  public TemperatureElement getTemperatureElement() {
    return temperatureElement;
  }

  public void setTemperatureElement(TemperatureElement temperatureElement) {
    this.temperatureElement = temperatureElement;
  }
}