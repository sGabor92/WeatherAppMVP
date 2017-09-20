package hu.webandmore.weatherappmvp.model;

public class WindData {

    private double speed;
    private double deg;

    public double getSpeed() {
        return speed;
    }

    public void setSpeed(double speed) {
        this.speed = speed;
    }

    public double getDeg() {
        return deg;
    }

    public void setDeg(double deg) {
        this.deg = deg;
    }

    public double getSpeedInKm(){
        return speed * 1.609344;
    }
}
