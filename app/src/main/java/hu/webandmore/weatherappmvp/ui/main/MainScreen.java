package hu.webandmore.weatherappmvp.ui.main;

import hu.webandmore.weatherappmvp.model.Location;

public interface MainScreen {
    void showWeather(Location location);

    void showNetworkError(String errorMsg);
}
