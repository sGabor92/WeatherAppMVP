package hu.webandmore.weatherappmvp.network;

import hu.webandmore.weatherappmvp.model.Location;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface WeatherApi {
    @GET("weather")
    Call<Location> getWeather(@Query("q") String city,
                              @Query("units") String units,
                              @Query("appid") String appid);
}
