package hu.webandmore.weatherappmvp.dagger;

import dagger.Module;
import dagger.Provides;
import hu.webandmore.weatherappmvp.interactor.WeatherInteractor;

@Module
public class InteractorModule {

    @Provides
    public WeatherInteractor provideWeatherInteractor() {
        return new WeatherInteractor();
    }

}
