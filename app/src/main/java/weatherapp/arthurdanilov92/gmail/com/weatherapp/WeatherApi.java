package weatherapp.arthurdanilov92.gmail.com.weatherapp;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface WeatherApi {
  @GET("data/2.5/weather")
  Call<WeatherModel> getWeatherByCityName(@Query("q") String cityName, @Query("appId") String appId);
}
