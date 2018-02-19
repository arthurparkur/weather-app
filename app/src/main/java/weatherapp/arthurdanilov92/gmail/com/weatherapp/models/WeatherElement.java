package weatherapp.arthurdanilov92.gmail.com.weatherapp.models;

import com.google.gson.annotations.SerializedName;

public class WeatherElement {
  @SerializedName("id")
  Integer id;
  @SerializedName("main")
  String  main;
  @SerializedName("description")
  String  description;
  @SerializedName("icon")
  String  icon;

  public String getIcon() {
    return icon;
  }

  public void setIcon(String icon) {
    this.icon = icon;
  }
}
