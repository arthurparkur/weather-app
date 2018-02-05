package weatherapp.arthurdanilov92.gmail.com.weatherapp;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class WeatherModel {

  @SerializedName("name")
  String name;
  @SerializedName("main")
  MainInfo main;
  @SerializedName("weather")
  List<WeatherInfo> weather;

  Long updatedDate;

  public WeatherModel() {
    this.main = new MainInfo();
    this.weather = new ArrayList<WeatherInfo>();
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Integer getHumidity() {
    return main.humidity;
  }

  public void setHumidity(Integer humidity) {
    main.humidity = humidity;
  }

  public Integer getPressure() {
    return main.pressure;
  }

  public void setPressure(Integer pressure) {
    main.pressure = pressure;
  }

  public Double getTemperature() {
    return main.temp;
  }

  public String getDescription() {
    return weather.get(0).description;
  }

  public void setDescription(String description) {
    int         l  = weather.size();
    WeatherInfo el = new WeatherInfo();
    el.description = description;
    weather.add(l, el);
  }

  public Long getUpdatedDate() {
    return updatedDate;
  }

  public void setUpdatedDate(Long updatedDate) {
    this.updatedDate = updatedDate;
  }

  public void setTemp(Double temp) {
    main.temp = temp;
  }

  public String getAndFormatWeatherInfo() {
    return "City: " + getName() + "\n"
            + "Temperature: " + getTemperature() + "\n"
            + "Humidity: " + getHumidity() + "\n"
            + "Pressure: " + getPressure() + "\n"
            + "Description: " + getDescription();
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
