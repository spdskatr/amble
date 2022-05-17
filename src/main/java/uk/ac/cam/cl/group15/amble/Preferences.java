package uk.ac.cam.cl.group15.amble;

import java.util.HashMap;

public class Preferences {
    private HashMap<String,pref> prefList;

    public Preferences(HashMap<String, pref> prefs){
        prefList = prefs;
    }

    public pref getPreference(String name){
        if(!prefList.containsKey(name)){
            throw new RuntimeException("Preference Requested does not exist");
        }
        return prefList.get(name);
    }

    public void setPreference(String name, pref preference){
        if(!prefList.containsKey(name)){
            throw new RuntimeException("Preference Requested does not exist");
        }
        else{
            prefList.put(name, preference);
        }
    }

}
