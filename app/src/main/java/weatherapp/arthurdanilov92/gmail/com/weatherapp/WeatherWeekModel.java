package weatherapp.arthurdanilov92.gmail.com.weatherapp;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class WeatherWeekModel {

  @SerializedName("city")
  City       city;
  @SerializedName("cnt")
  Integer    cnt;
  @SerializedName("list")
  List<Item> list;
  @SerializedName("message")
  Float      message;

  Long updatedDate;

  public WeatherWeekModel() {
    this.city = new City();
    this.list = new ArrayList<Item>();
  }

  public String getName() {
    return city.name;
  }

  public void setName(String name) {
    this.city.name = name;
  }

  public Float getTemp(Integer day) {
    return list.get(day - 1).temperatureElement.day;
  }

  public String getIcon(Integer day) {
    return list.get(day - 1).weatherElements.get(0).icon;
  }

  public void setTemp(Float temp) {
    Item item = new Item();
    item.temperatureElement.day = temp;
    list.add(item);
  }

  public void setIcon(Integer day, String icon) {
    if (list.get(day - 1) == null) {
      Item item = new Item();
      item.weatherElements.get(0).icon = icon;
      list.add(item);
    }
    list.get(day - 1).weatherElements.get(0).icon = icon;
  }

  public Long getUpdatedDate() {
    return updatedDate;
  }

  public void setUpdatedDate(Long updatedDate) {
    this.updatedDate = updatedDate;
  }

  private class Item {
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

    public Item() {
      this.weatherElements = new ArrayList<WeatherElement>();
      weatherElements.add(new WeatherElement());
      this.temperatureElement = new TemperatureElement();
    }
  }

  private class TemperatureElement {

    @SerializedName("day")
    Float day;
    @SerializedName("eve")
    Float eve;
    @SerializedName("max")
    Float max;
    @SerializedName("min")
    Float min;
    @SerializedName("morn")
    Float morn;
    @SerializedName("night")
    Float night;
  }

  private class WeatherElement {
    @SerializedName("id")
    Integer id;
    @SerializedName("main")
    String  main;
    @SerializedName("description")
    String  description;
    @SerializedName("icon")
    String  icon;
  }

  private class City {

    @SerializedName("geoname_id")
    Integer geoname_id;
    @SerializedName("name")
    String  name;
    @SerializedName("country")
    String  country;
    @SerializedName("lat")
    float   lat;
    @SerializedName("lon")
    float   lon;
    @SerializedName("population")
    Integer population;
    @SerializedName("type")
    String  type;
  }
}
