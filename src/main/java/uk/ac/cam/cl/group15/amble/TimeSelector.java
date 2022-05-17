package uk.ac.cam.cl.group15.amble;

import java.util.ArrayList;
import java.util.TreeMap;

public class TimeSelector {
    private static ArrayList<SimpleWeather> hourlyWeather;

    //inputs a list of the Weather every hour this day and the duration of walk in minutes
    public static WalkTime chooseTime(ArrayList<SimpleWeather> today, int duration){
        hourlyWeather = new ArrayList<>();
        Preferences weatherPrefs = PrefTest.defaultWeatherPref(); //eventually will be stored as a file
        Preferences timePrefs = PrefTest.defaultTimePref(); //eventually will be stored as a file

        for(SimpleWeather w : today){
            if(!timePrefs.getPreference(w.getHour() + "").equals(pref.NEVER)) {
                hourlyWeather.add(w);
            }
        }
        TreeMap<pref,ArrayList<SimpleWeather>> storage = new TreeMap<>();

        for(SimpleWeather w: hourlyWeather){
            String name = w.getName();
            pref p = weatherPrefs.getPreference(name);
            if(storage.containsKey(p)){
                storage.get(p).add(w);
            }
            else{
                ArrayList<SimpleWeather> a = new ArrayList();
                a.add(w);
                storage.put(p,a);
            }
        }

        if(storage.firstKey().equals(pref.NEVER)){
            System.out.println("L + Ratio + Oh no you can't walk");
            return null;
        }
        ArrayList<SimpleWeather> ideal = storage.firstEntry().getValue();

        TreeMap<pref,ArrayList<SimpleWeather>> timeStorage = new TreeMap<>();
        for(SimpleWeather i : ideal){
            int time = i.getHour();
            pref p = timePrefs.getPreference(time + "");
            if(timeStorage.containsKey(p)){
                timeStorage.get(p).add(i);
            }
            else{
                ArrayList<SimpleWeather> a = new ArrayList();
                a.add(i);
                timeStorage.put(p,a);
            }
        }

        SimpleWeather idealWalk = timeStorage.firstEntry().getValue().get(0);
        int startTime = timeStorage.firstEntry().getValue().get(0).getHour();
        String idealWalkTime;
        if(startTime < 10){
            idealWalkTime = "0" + startTime + ":00";
        }
        else{
            idealWalkTime = startTime + ":00";
        }

        //now to calculate the actual time you're walking

        int hours = duration / 60;
        int minutes = duration - (hours * 60);

        hours = hours + startTime;
        String endTime;
        if(hours < 10){
            if(minutes < 10){
                endTime = "0" + hours + ":0" + minutes;
            }
            else {
                endTime = "0" + hours + ":" + minutes;
            }
        }
        else{
            if(minutes < 10){
                endTime = hours + ":0" + minutes;
            }
            else {
                endTime = hours + ":" + minutes;
            }
        }

        return new WalkTime(idealWalk, idealWalkTime, endTime);
    }
}
