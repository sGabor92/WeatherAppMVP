package hu.webandmore.weatherappmvp.ui.main;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import hu.webandmore.weatherappmvp.interactor.WeatherInteractor;
import hu.webandmore.weatherappmvp.interactor.event.GetWeatherEvent;
import hu.webandmore.weatherappmvp.model.Location;
import hu.webandmore.weatherappmvp.ui.Presenter;

public class MainPresenter extends Presenter<MainScreen> {

    private static MainPresenter instance = null;
    private Executor networkExecutor;
    private WeatherInteractor weatherInteractor;
    private Location prevResult;

    private MainPresenter() {
        networkExecutor = Executors.newFixedThreadPool(1);
        weatherInteractor = new WeatherInteractor();
    }

    public static MainPresenter getInstance() {
        if (instance == null) {
            instance = new MainPresenter();
        }
        return instance;
    }

    @Override
    public void attachScreen(MainScreen screen) {
        super.attachScreen(screen);
        EventBus.getDefault().register(this);
    }

    @Override
    public void detachScreen() {
        EventBus.getDefault().unregister(this);
        super.detachScreen();
    }

    public void showLocationWeather(final String location) {
        networkExecutor.execute(new Runnable() {
            @Override
            public void run() {
                weatherInteractor.getWeather(location);
            }
        });
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEventMainThread(final GetWeatherEvent event) {
        if (event.getThrowable() != null) {
            event.getThrowable().printStackTrace();
            if (screen != null) {
                screen.showNetworkError(event.getThrowable().getMessage());
            }
        } else {
            if (screen != null) {
                prevResult = event.getLocationWeather();
                screen.showWeather(event.getLocationWeather());
            }
        }
    }

    public Location getPrevResult() {
        return prevResult;
    }

}
