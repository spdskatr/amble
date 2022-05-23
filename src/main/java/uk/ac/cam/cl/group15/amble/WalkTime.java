package uk.ac.cam.cl.group15.amble;

import java.time.LocalTime;

public class WalkTime {
    final SimpleWeather currentWeather;
    final LocalTime startTime;
    final LocalTime endTime;

    //format startTime and endTime strings as "06:30" or "15:32"
    public WalkTime(SimpleWeather weather, String startTime, String endTime){
        currentWeather = weather;
        this.startTime = LocalTime.parse(startTime);
        this.endTime = LocalTime.parse(endTime);
    }

    public SimpleWeather getCurrentWeather() {
        return currentWeather;
    }

    public LocalTime getStartTime() {
        return startTime;
    }

    public LocalTime getEndTime() {
        return endTime;
    }

    @Override
    public String toString() {
        return startTime + " - " + endTime;
    }
}

