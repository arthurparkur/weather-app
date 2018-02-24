package weatherapp.arthurdanilov92.gmail.com.weatherapp;

import android.app.Application;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class App extends Application {
  public static final String APP_KEY = "10792cf43e4992f76dfdb635ad0be4eb";
  private static IWeatherApi weatherApi;
  private        Retrofit    retrofit;

  public static IWeatherApi getWeatherApi() {
    return weatherApi;
  }

  @Override
  public void onCreate() {
    super.onCreate();

    retrofit = new Retrofit.Builder()
            .baseUrl("http://api.openweathermap.org/")
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    weatherApi = retrofit.create(IWeatherApi.class);
  }
}
