package uk.ac.cam.cl.group15.amble;

public class WalkingAlgorithms {
    public static WalkTime DailyIdealWalk(MainController mainController){
        WalkTime time = TimeSelector.chooseTime(mainController.forecast,30); //TODO: Make this include preferences
        return time;
    }
}
