package weatherapp.arthurdanilov92.gmail.com.weatherapp.models;

import com.google.gson.annotations.SerializedName;

public class TemperatureElement {

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

  public Float getDay() {
    return day;
  }

  public void setDay(Float day) {
    this.day = day;
  }
}
