package weatherapp.arthurdanilov92.gmail.com.weatherapp.models;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class WeatherWeekModel {

  @SerializedName("city")
  City                  city;
  @SerializedName("cnt")
  Integer               cnt;
  @SerializedName("list")
  List<WeatherWeekItem> list;
  @SerializedName("message")
  Float                 message;

  Long updatedDate;

  public WeatherWeekModel() {
    this.city = new City();
    this.list = new ArrayList<WeatherWeekItem>();
  }

  public List<WeatherWeekItem> getWeekInfo() {
    return list;
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
    WeatherWeekItem item = new WeatherWeekItem();
    item.temperatureElement.day = temp;
    list.add(item);
  }

  public void setIcon(Integer day, String icon) {
    if (list.get(day - 1) == null) {
      WeatherWeekItem item = new WeatherWeekItem();
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

  public class City {

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
