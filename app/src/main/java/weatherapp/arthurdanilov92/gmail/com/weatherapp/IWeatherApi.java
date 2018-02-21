package weatherapp.arthurdanilov92.gmail.com.weatherapp;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
import weatherapp.arthurdanilov92.gmail.com.weatherapp.models.WeatherModel;
import weatherapp.arthurdanilov92.gmail.com.weatherapp.models.WeatherWeekModel;

public interface IWeatherApi {
  @GET("data/2.5/weather")
  Call<WeatherModel> getWeatherByCityName(@Query("q") String cityName,
                                          @Query("units") String units,
                                          @Query("appId") String appId);

  @GET("data/2.5/forecast/daily")
  Call<WeatherWeekModel> getWeatherWeekByCityName(@Query("q") String cityName,
                                                  @Query("units") String units,
                                                  @Query("cnt") Integer cnt,
                                                  @Query("appId") String appId);
}
