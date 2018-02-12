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

  public String getHumidity() {
    StringBuffer humidityLine = new StringBuffer();
    for (int i = 0; i < list.size(); i++) {
      humidityLine.append(list.get(i).humidity + " ");
    }
    return humidityLine.toString();
  }

  public String getPressure() {
    StringBuffer pressureLine = new StringBuffer();
    for (int i = 0; i < list.size(); i++) {
      pressureLine.append(list.get(i).pressure + " ");
    }
    return pressureLine.toString();
  }

  public String getTemperature() {
    StringBuffer temperatureLine = new StringBuffer();
    for (int i = 0; i < list.size(); i++) {
      temperatureLine.append(list.get(i).temperatureElement.day + " ");
    }
    return temperatureLine.toString();
  }

  private class Item {

    @SerializedName("clouds")
    Integer clouds;

    @SerializedName("deg")
    Integer deg;

    @SerializedName("dt")
    Float dt;

    @SerializedName("humidity")
    Integer humidity;

    @SerializedName("pressure")
    Float pressure;

    @SerializedName("snow")
    Float snow;

    @SerializedName("speed")
    Float speed;

    @SerializedName("weather")
    List<WeatherElement> weatherElements;

    @SerializedName("temperature")
    TemperatureElement temperatureElement;

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
    String name;

    @SerializedName("country")
    String country;

    @SerializedName("lat")
    float lat;

    @SerializedName("lon")
    float lon;

    @SerializedName("population")
    Integer population;

    @SerializedName("type")
    String type;
  }
}
