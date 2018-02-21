package weatherapp.arthurdanilov92.gmail.com.weatherapp.models;

import android.text.TextUtils;

import java.util.ArrayList;
import java.util.List;

public class WeatherIntegrate {

  String                name;
  MainInfo              mainInfoToday;
  List<WeatherInfo>     weatherToday;
  List<WeatherWeekItem> weekInfo;

  Long updatedDate;

  public WeatherIntegrate(WeatherModel t, WeatherWeekModel w) {
    this.name = t.getName();
    this.mainInfoToday = t.getMain();
    this.weatherToday = t.getWeather();
    this.weekInfo = w.getWeekInfo();
  }

  public WeatherIntegrate() {
    this.mainInfoToday = new MainInfo();
    this.weatherToday = new ArrayList<WeatherInfo>();
    this.weekInfo = new ArrayList<WeatherWeekItem>();
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Integer getHumidity() {
    return mainInfoToday.getHumidity();
  }

  public void setHumidity(Integer humidity) {
    mainInfoToday.setHumidity(humidity);
  }

  public Integer getPressure() {
    return mainInfoToday.getPressure();
  }

  public void setPressure(Integer pressure) {
    mainInfoToday.setPressure(pressure);
  }

  public Double getTemperature() {
    return mainInfoToday.getTemp();
  }

  public void setTemp(Double temp) {
    mainInfoToday.setTemp(temp);
  }

  public String getAndFormatWeatherInfo() {
    return "City: " + getName() + "\n"
            + "Temperature: " + getTemperature() + "\n"
            + "Humidity: " + getHumidity() + "\n"
            + "Pressure: " + getPressure() + "\n"
            + "Description: " + getDescription();
  }

  public String getDescription() {
    return weatherToday.get(0).getDescription();
  }

  public void setDescription(String description) {
    int         l  = weatherToday.size();
    WeatherInfo el = new WeatherInfo();
    el.setDescription(description);
    weatherToday.add(l, el);
  }

  public Float getTemp(Integer day) {
    return weekInfo.get(day - 1).getTemperatureElement().getDay();
  }

  public String getDate(Integer day) {
    return weekInfo.get(day - 1).getDt();
  }

  public void setDate(Integer day, String date) {
    weekInfo.get(day - 1).setDt(date);
  }

  public String getIcon(Integer day) {
    return weekInfo.get(day - 1).getWeatherElements().get(0).getIcon();
  }

  public String getTodayIcon() {
    return weatherToday.get(0).getIcon();
  }

  public void setTodayIcon(String icon) {
    weatherToday.get(0).setIcon(icon);
  }

  public void setTemp(Float temp) {
    WeatherWeekItem item = new WeatherWeekItem();
    item.getTemperatureElement().setDay(temp);
    weekInfo.add(item);
  }

  public void setIcon(Integer day, String icon) {
    if (weekInfo.get(day - 1) == null) {
      WeatherWeekItem item = new WeatherWeekItem();
      item.getWeatherElements().get(0).setIcon(icon);
      weekInfo.add(item);
    }
    weekInfo.get(day - 1).getWeatherElements().get(0).setIcon(icon);
  }

  public List<WeatherWeekItem> getWeekInfo() {
    return weekInfo;
  }

  public boolean isEmpty() {
    return (TextUtils.isEmpty(getName())) || (weekInfo.size() == 0);
  }

  public Long getUpdatedDate() {
    return updatedDate;
  }

  public void setUpdatedDate(Long updatedDate) {
    this.updatedDate = updatedDate;
  }
}
