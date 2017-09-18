package hu.webandmore.weatherappmvp.interactor;

import org.greenrobot.eventbus.EventBus;

import hu.webandmore.weatherappmvp.interactor.event.GetWeatherEvent;
import hu.webandmore.weatherappmvp.model.Location;
import hu.webandmore.weatherappmvp.network.WeatherApi;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;

public class WeatherInteractor {

    private Retrofit retrofit;
    WeatherApi weatherApi;

    public WeatherInteractor() {
        //WeatherApplication.injector.inject(this);
    }

    public void getWeather(String city) {
        Call<Location> artistsQueryCall = weatherApi.getWeather(city, "metric", "f3d694bc3e1d44c1ed5a97bd1120e8fe");
        GetWeatherEvent event = new GetWeatherEvent();
        try {
            Response<Location> response = artistsQueryCall.execute();
            if (response.code() != 200) {
                throw new Exception("Result code is not 200");
            }
            event.setCode(response.code());
            event.setLocation(response.body());
            EventBus.getDefault().post(event);
        } catch (Exception e) {
            event.setThrowable(e);
            EventBus.getDefault().post(event);
        }
    }
}
