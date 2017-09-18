package hu.webandmore.weatherappmvp.interactor.event;

import hu.webandmore.weatherappmvp.model.Location;

public class GetWeatherEvent {
    private int code;
    private Location location;
    private Throwable throwable;

    public GetWeatherEvent() {
    }

    public GetWeatherEvent(int code, Location location, Throwable throwable) {
        this.code = code;
        this.location = location;
        this.throwable = throwable;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public Location getLocationWeather() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public Throwable getThrowable() {
        return throwable;
    }

    public void setThrowable(Throwable throwable) {
        this.throwable = throwable;
    }
}
