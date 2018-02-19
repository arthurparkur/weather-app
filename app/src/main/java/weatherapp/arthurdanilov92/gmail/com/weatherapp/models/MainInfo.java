package weatherapp.arthurdanilov92.gmail.com.weatherapp.models;

import com.google.gson.annotations.SerializedName;

public class MainInfo {

  @SerializedName("humidity")
  Integer humidity;
  @SerializedName("pressure")
  Integer pressure;
  @SerializedName("temp")
  Double  temp;

  public Integer getHumidity() {
    return humidity;
  }

  public void setHumidity(Integer humidity) {
    this.humidity = humidity;
  }

  public Integer getPressure() {
    return pressure;
  }

  public void setPressure(Integer pressure) {
    this.pressure = pressure;
  }

  public Double getTemp() {
    return temp;
  }

  public void setTemp(Double temp) {
    this.temp = temp;
  }
}