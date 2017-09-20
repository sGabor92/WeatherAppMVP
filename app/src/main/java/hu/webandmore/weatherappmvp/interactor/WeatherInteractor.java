package hu.webandmore.weatherappmvp.interactor;

import org.greenrobot.eventbus.EventBus;

import javax.inject.Inject;

import hu.webandmore.weatherappmvp.app.WeatherApplication;
import hu.webandmore.weatherappmvp.interactor.event.GetWeatherEvent;
import hu.webandmore.weatherappmvp.model.Location;
import hu.webandmore.weatherappmvp.network.NetworkConfig;
import hu.webandmore.weatherappmvp.network.WeatherApi;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class WeatherInteractor {

    @Inject
    WeatherApi weatherApi;

    public WeatherInteractor() {
        WeatherApplication.injector.inject(this);
    }

    public void getWeather(String city) {
        Call<Location> weatherQueryCall = weatherApi.getWeather(city, "metric", "f3d694bc3e1d44c1ed5a97bd1120e8fe");
        GetWeatherEvent event = new GetWeatherEvent();
        try {
            Response<Location> response = weatherQueryCall.execute();
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
