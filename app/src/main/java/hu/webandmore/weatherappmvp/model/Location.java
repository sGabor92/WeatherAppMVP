package hu.webandmore.weatherappmvp.model;

public class Location {

    private int id;
    private String name;
    private int code;
    private Coordination coord;
    private Weather[] weather;
    private TemperatureData main;
    private WindData wind;
    private CloudModel clouds;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public Coordination getCoord() {
        return coord;
    }

    public void setCoord(Coordination coord) {
        this.coord = coord;
    }

    public Weather[] getWeather() {
        return weather;
    }

    public void setWeather(Weather[] weather) {
        this.weather = weather;
    }

    public TemperatureData getMain() {
        return main;
    }

    public void setMain(TemperatureData main) {
        this.main = main;
    }

    public WindData getWind() {
        return wind;
    }

    public void setWind(WindData wind) {
        this.wind = wind;
    }

    public CloudModel getClouds() {
        return clouds;
    }

    public void setClouds(CloudModel clouds) {
        this.clouds = clouds;
    }
}
