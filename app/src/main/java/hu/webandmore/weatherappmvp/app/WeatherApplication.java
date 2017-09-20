package hu.webandmore.weatherappmvp.app;

import android.app.Application;

import hu.webandmore.weatherappmvp.dagger.AppComponent;
import hu.webandmore.weatherappmvp.dagger.AppModule;
import hu.webandmore.weatherappmvp.dagger.DaggerAppComponent;

public class WeatherApplication extends Application{

    public static AppComponent injector;

    @Override
    public void onCreate() {
        super.onCreate();

        injector =
                DaggerAppComponent.builder().
                        appModule(
                                new AppModule(this)
                        ).build();
    }

}
