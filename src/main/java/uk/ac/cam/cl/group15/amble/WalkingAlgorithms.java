package uk.ac.cam.cl.group15.amble;

import uk.ac.cam.cl.group15.amble.openweathermapapi.InvalidTimeException;

import java.util.ArrayList;

public class WalkingAlgorithms {
    public static WalkTime DailyIdealWalk(MainController mainController) throws InvalidTimeException {
        WalkTime time = TimeSelector.chooseTime(mainController.forecast,30, mainController.weatherPref, mainController.timePref); //TODO: Make this include preferences
        return time;
    }

    public static ArrayList<WalkTime> TopThree30MinWalks(MainController mainController) throws InvalidTimeException {
        ArrayList<WalkTime> times = TimeSelector.chooseTime(mainController.forecast, 30,3, mainController.weatherPref, mainController.timePref);
        return times;
    }

    public static ArrayList<WalkTime> TopThree60MinWalks(MainController mainController) throws InvalidTimeException {
        ArrayList<WalkTime> times = TimeSelector.chooseTime(mainController.forecast, 60,3, mainController.weatherPref, mainController.timePref);
        return times;
    }

    public static ArrayList<WalkTime> TopThree120MinWalks(MainController mainController) throws InvalidTimeException {
        ArrayList<WalkTime> times = TimeSelector.chooseTime(mainController.forecast, 120,3, mainController.weatherPref, mainController.timePref);
        return times;
    }
}
