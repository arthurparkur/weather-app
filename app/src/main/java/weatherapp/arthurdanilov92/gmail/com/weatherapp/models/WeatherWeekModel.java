package weatherapp.arthurdanilov92.gmail.com.weatherapp.models;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class WeatherWeekModel {

  City                  city;
  List<WeatherWeekItem> list;

  public WeatherWeekModel() {
    this.city = new City();
    this.list = new ArrayList<>();
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
