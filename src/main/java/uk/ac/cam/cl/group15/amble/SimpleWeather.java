package uk.ac.cam.cl.group15.amble;

public class SimpleWeather {
    private final String name;
    private final int hour;

    public SimpleWeather(String name, int hour){
        this.name = name;
        this.hour = hour;
    }

    public String getName() {
        return name;
    }

    public int getHour() {
        return hour;
    }
}
