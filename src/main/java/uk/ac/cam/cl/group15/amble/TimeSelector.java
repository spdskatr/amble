package uk.ac.cam.cl.group15.amble;

import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;

public class TimeSelector {
    private static ArrayList<SimpleWeather> hourlyWeather;

    //inputs a list of the Weather every hour this day and the duration of walk in minutes
    public static WalkTime chooseTime(ArrayList<SimpleWeather> today, int duration, Preferences weatherPref, Preferences timePref){
        hourlyWeather = new ArrayList<>();
        Preferences weatherPrefs = weatherPref;
        Preferences timePrefs = timePref;

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
                ArrayList<SimpleWeather> a = new ArrayList<>();
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
                ArrayList<SimpleWeather> a = new ArrayList<>();
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
            if(hours == 24){
                hours = 0;
                if(minutes < 10){
                    endTime = "0" + hours + ":0" + minutes;
                }
                else {
                    endTime = "0" + hours + ":" + minutes;
                }
            }
            else {
                if (minutes < 10) {
                    endTime = hours + ":0" + minutes;
                } else {
                    endTime = hours + ":" + minutes;
                }
            }
        }

        return new WalkTime(idealWalk, idealWalkTime, endTime);
    }

    public static ArrayList<WalkTime> chooseTime(ArrayList<SimpleWeather> today, int duration, int n, Preferences weatherPref, Preferences timePref){
        if(n > today.size()){
            throw new RuntimeException("Requesting more times then there are hours in the day.");
        }

        hourlyWeather = new ArrayList<>();
        Preferences weatherPrefs = weatherPref;
        Preferences timePrefs = timePref;

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
                ArrayList<SimpleWeather> a = new ArrayList<>();
                a.add(w);
                storage.put(p,a);
            }
        }

        if(storage.firstKey().equals(pref.NEVER)){
            System.out.println("L + Ratio + Oh no you can't walk");
            return null;
        }

        ArrayList<WalkTime> walkTimes = new ArrayList<>();

        if(storage.firstEntry().getValue().size() >= n){
            //function as expected
            ArrayList<SimpleWeather> ideal = storage.firstEntry().getValue();

            TreeMap<pref,ArrayList<SimpleWeather>> timeStorage = new TreeMap<>();
            for(SimpleWeather i : ideal){
                int time = i.getHour();
                pref p = timePrefs.getPreference(time + "");
                if(timeStorage.containsKey(p)){
                    timeStorage.get(p).add(i);
                }
                else{
                    ArrayList<SimpleWeather> a = new ArrayList<>();
                    a.add(i);
                    timeStorage.put(p,a);
                }
            }

            //now to go through the set amount needed
            int count = 0;
            for(Map.Entry<pref, ArrayList<SimpleWeather>> entry : timeStorage.entrySet()) {
                for(SimpleWeather chosenWeather: entry.getValue()){
                    if(count == n){
                        break;
                    }

                    int startTime = chosenWeather.getHour();
                    ArrayList<String> times = formatWalkTimes(startTime,duration);

                    walkTimes.add(new WalkTime(chosenWeather, times.get(0), times.get(1)));
                    count++;
                }
                if(count == n){
                    break;
                }
            }

        }
        else{
            //fear
            int listsToUse = 0;
            int size = 0;
            for(Map.Entry<pref, ArrayList<SimpleWeather>> entry : storage.entrySet()){
                if(size >= n){
                    break;
                }
                size = size + entry.getValue().size();
                listsToUse++;
            }

            int count = 0;
            for(int i = 0; i < listsToUse; i++){
                //this time we poll the first entry so we get the first n entries overall
                ArrayList<SimpleWeather> ideal = storage.pollFirstEntry().getValue();

                TreeMap<pref,ArrayList<SimpleWeather>> timeStorage = new TreeMap<>();
                for(SimpleWeather j : ideal){
                    int time = j.getHour();
                    pref p = timePrefs.getPreference(time + "");
                    if(timeStorage.containsKey(p)){
                        timeStorage.get(p).add(j);
                    }
                    else{
                        ArrayList<SimpleWeather> a = new ArrayList<>();
                        a.add(j);
                        timeStorage.put(p,a);
                    }
                }

                for(Map.Entry<pref, ArrayList<SimpleWeather>> entry : timeStorage.entrySet()) {
                    for(SimpleWeather chosenWeather: entry.getValue()){
                        if(count == n){
                            break;
                        }

                        int startTime = chosenWeather.getHour();
                        ArrayList<String> times = formatWalkTimes(startTime,duration);

                        walkTimes.add(new WalkTime(chosenWeather, times.get(0), times.get(1)));
                        count++;
                    }
                    if(count == n){
                        break;
                    }
                }
            }
        }

        return walkTimes;
    }



    public static ArrayList<String> formatWalkTimes(int startTime, int duration){
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
            if(hours == 24){
                hours = 0;
                if(minutes < 10){
                    endTime = "0" + hours + ":0" + minutes;
                }
                else {
                    endTime = "0" + hours + ":" + minutes;
                }
            }
            else {
                if (minutes < 10) {
                    endTime = hours + ":0" + minutes;
                } else {
                    endTime = hours + ":" + minutes;
                }
            }
        }

        ArrayList<String> pair = new ArrayList<>();
        pair.add(idealWalkTime);
        pair.add(endTime);

        return pair;
    }

    //TODO: Work out why it's not showing walks at midnight (ask Maan if this is on purpose)

    //TODO: Then sort out the preferences menu itself - make sure that Time Selector functions are all rerun upon preferences changing otherwise it'll look like it's had no effect lmao

    //TODO: Maybe implement away to get the best time to walk the next day
}
