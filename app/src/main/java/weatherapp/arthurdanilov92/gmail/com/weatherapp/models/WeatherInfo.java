package weatherapp.arthurdanilov92.gmail.com.weatherapp.models;

import com.google.gson.annotations.SerializedName;

public class WeatherInfo {
  @SerializedName("description")
  String description;
  @SerializedName("icon")
  String icon;

  public String getDescription() {

    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public String getIcon() {
    return icon;
  }

  public void setIcon(String icon) {
    this.icon = icon;
  }
}