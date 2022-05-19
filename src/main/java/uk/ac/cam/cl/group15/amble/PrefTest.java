package uk.ac.cam.cl.group15.amble;

import java.util.HashMap;

public class PrefTest {
    static Preferences defaultWeatherPref(){
        HashMap<String, pref> weather = new HashMap<>();
        weather.put("Sunny", pref.VERYHIGH);
        weather.put("Partly Cloudy",pref.HIGH);
        weather.put("Cloudy",pref.MEDIUM);
        weather.put("Light Rain", pref.LOW);
        weather.put("Rain", pref.VERYLOW);
        weather.put("Snow", pref.MEDIUM);
        weather.put("Mist", pref.LOW);
        weather.put("Thunderstorm", pref.NEVER);
        weather.put("Danger", pref.NEVER);

        return new Preferences(weather);
    }

    static Preferences defaultTimePref(){
        HashMap<String, pref> time = new HashMap<>();
        time.put("0", pref.NEVER);
        time.put("1",pref.NEVER);
        time.put("2", pref.NEVER);
        time.put("3", pref.NEVER);
        time.put("4", pref.NEVER);
        time.put("5", pref.NEVER);
        time.put("6", pref.VERYLOW);
        time.put("7", pref.LOW);
        time.put("8",pref.LOW);
        time.put("9", pref.MEDIUM);
        time.put("10", pref.MEDIUM);
        time.put("11", pref.MEDIUM);
        time.put("12", pref.HIGH);
        time.put("13", pref.HIGH);
        time.put("14", pref.VERYHIGH);
        time.put("15",pref.VERYHIGH);
        time.put("16", pref.VERYHIGH);
        time.put("17", pref.VERYHIGH);
        time.put("18", pref.HIGH);
        time.put("19", pref.HIGH);
        time.put("20", pref.HIGH);
        time.put("21", pref.HIGH);
        time.put("22", pref.LOW);
        time.put("23", pref.LOW);

        return new Preferences(time);
    }
}
