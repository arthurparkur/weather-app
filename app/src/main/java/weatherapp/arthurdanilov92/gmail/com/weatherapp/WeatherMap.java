package weatherapp.arthurdanilov92.gmail.com.weatherapp;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class WeatherMap {

  @SerializedName("name")
  String name;
  @SerializedName("main")
  MainInfo main;
  @SerializedName("weather")
  List<WeatherInfo> weather;

  public String getName() {
    return name;
  }

  public Integer getHumidity() {
    return main.humidity;
  }

  public Integer getPressure() {
    return main.pressure;
  }

  public Double getTemperature() {
    return main.temp;
  }

  public String getDescription() {
    return weather.get(0).description;
  }

  private class MainInfo {
    @SerializedName("humidity")
    Integer humidity;
    @SerializedName("pressure")
    Integer pressure;
    @SerializedName("temp")
    Double  temp;
  }

  private class WeatherInfo {
    @SerializedName("description")
    String description;
  }
}
