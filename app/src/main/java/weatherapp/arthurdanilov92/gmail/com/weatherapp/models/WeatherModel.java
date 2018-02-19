package weatherapp.arthurdanilov92.gmail.com.weatherapp.models;

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

  public String getIcon() {
    return weather.get(0).icon;
  }

  public void setIcon(String icon) {
    int         l  = weather.size();
    WeatherInfo el = new WeatherInfo();
    el.icon = icon;
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
}
