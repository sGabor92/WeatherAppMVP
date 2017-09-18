package hu.webandmore.weatherappmvp.ui.main;

public interface MainScreen {
    void showWeather(String location);

    void showNetworkError(String errorMsg);
}
