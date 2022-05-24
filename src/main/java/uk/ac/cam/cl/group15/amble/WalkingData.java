package uk.ac.cam.cl.group15.amble;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.time.*;
import java.util.ArrayList;
import java.util.HashMap;

public class WalkingData {


    ArrayList<Walk> walks;
    private double totalDistance = 0;
    private double totalWalks = 0;


    WalkingData(){
        walks = new ArrayList<>();
    }


    WalkingData(double totalDistance, double totalWalks) {
        this.totalDistance = totalDistance;
        this.totalWalks = totalWalks;
    }


    static Walk dummyWalk(){
        return new Walk(0, LocalDateTime.now(), LocalDateTime.now());
    }

    public void inputWalk(){
        Walk walk = dummyWalk();
        insert(walk);
    }

    public void inputWalk(double distance){
        Walk walk = new Walk(distance, LocalDateTime.now(), LocalDateTime.now());
        insert(walk);
    }

    public void inputWalk(double distance, LocalDateTime start, LocalDateTime end){
        Walk walk = new Walk(distance, start, end);
        insert(walk);
    }

    private void insert(Walk walk){
        LocalDateTime lambda = walk.getStartTime();
        boolean added = Boolean.FALSE;
        for (int i=0; i<walks.size(); i++) {
            if (walks.get(i).getStartTime().isAfter(lambda)) {
                walks.add(i, walk);
                added = Boolean.TRUE;
                break;
            }
        }
        if (!added) {
            walks.add(walk);
        }
        totalDistance += walk.getDistance();
        totalWalks += 1;
    }

    public double getTotalDistance() {
        return totalDistance;
    }

    public double getTotalWalks() {
        return totalWalks;
    }

    public double getAverageDistance() {
        return totalDistance/totalWalks;
    }

    public ArrayList<Walk> getLastWeek() {
        LocalTime midnight = LocalTime.MIDNIGHT;
        LocalDate today = LocalDate.now(ZoneId.of("Europe/Berlin"));
        LocalDateTime todayMidnight = LocalDateTime.of(today, midnight);
        int i = walks.size();
        LocalDateTime weekAgo = todayMidnight.minus(6, ChronoUnit.DAYS);
        while (true){
            if (i==0){
                return walks;
            }
            i--;
            if (weekAgo.isAfter(walks.get(i).getStartTime())) {
                return new ArrayList<>(walks.subList(i+1, walks.size()-1));
            }
        }
    }

    public double getTotalDistanceLastWeek(){
        double totalDistance = 0;
        for (Walk walk : getLastWeek()) {
            totalDistance += walk.getDistance();
        }
        return totalDistance;
    }


    public int getTotalWalksLastWeek() {
        return getLastWeek().size();
    }

    public double getAverageDistanceLastWeek() {
        return getTotalDistanceLastWeek()/getTotalWalksLastWeek();
    }

    private HashMap<Integer, ArrayList<Walk>> splitWeekIntoDays() {
        HashMap<Integer, ArrayList<Walk>> daysSplit = new HashMap<>();
        for (int i=1; i<8; i++){
            daysSplit.put(i, new ArrayList<>());
        }
        ArrayList<Walk> lastWeek = getLastWeek();
        for (Walk walk : lastWeek){
            // Finds the day of week - converts to int - then adds to hashmap
            daysSplit.get(walk.getStartTime().getDayOfWeek().getValue()).add(walk);
        }
        return daysSplit;
    }


    public ArrayList<Double> getTotalDistanceLastWeekInDays() {
        ArrayList<Double> data = new ArrayList<>();
        HashMap<Integer, ArrayList<Walk>> splitWeek= this.splitWeekIntoDays();
        for (int i=1; i<8; i++) {
            data.add(getTotalDistance(splitWeek.get(i)));
        }
        return data;
    }


    public ArrayList<Double> getTotalWalksLastWeekInDays() {
        ArrayList<Double> data = new ArrayList<>();
        HashMap<Integer, ArrayList<Walk>> splitWeek= this.splitWeekIntoDays();
        for (int i=1; i<8; i++) {
            data.add(getTotalWalks(splitWeek.get(i)));
        }
        return data;
    }


    public ArrayList<Double> getAverageDistanceLastWeekInDays() {
        ArrayList<Double> data = new ArrayList<>();
        HashMap<Integer, ArrayList<Walk>> splitWeek= this.splitWeekIntoDays();
        for (int i=1; i<8; i++) {
            data.add(getAverageDistance(splitWeek.get(i)));
        }
        return data;
    }


    public ArrayList<ArrayList<Double>> getDataLastWeekInDays() {
        ArrayList<ArrayList<Double>> data = new ArrayList<>();
        HashMap<Integer, ArrayList<Walk>> splitWeek= this.splitWeekIntoDays();
        for (int i=1; i<8; i++) {
            ArrayList<Double> dayData = new ArrayList<>();
            dayData.add(getTotalDistance(splitWeek.get(i)));
            dayData.add(getTotalWalks(splitWeek.get(i)));
            dayData.add(getAverageDistance(splitWeek.get(i)));
            data.add(dayData);
        }
        return data;
    }


    private static double getTotalDistance(ArrayList<Walk> walkData){
        double totalDistance = 0;
        for (Walk walk : walkData) {
            totalDistance += walk.getDistance();
        }
        return totalDistance;
    }


    private static double getTotalWalks(ArrayList<Walk> walkData) {
        return walkData.size();
    }


    private static double getAverageDistance(ArrayList<Walk> walkData) {
        return (getTotalDistance(walkData)/getTotalWalks(walkData));
    }


    public static WalkingData sampleData() {
        double[] DISTS = new double[] { 1.36, 0, 0.57, 1.32, 1.18, 0, 2.19 };
        WalkingData data = new WalkingData();
        LocalDateTime now = LocalDateTime.now();
        for (int days=0; days < 7; days++){
            double dist = DISTS[days];
            LocalDateTime endTime = now.minus(days, ChronoUnit.DAYS);
            LocalDateTime startTime = endTime.minus(2, ChronoUnit.HOURS);
            data.inputWalk(dist, startTime, endTime);
        }
        data.inputWalk();
        return data;
    }


    @Override
    public String toString() {
        return "WalkingData{" +
                "walks=" + walks +
                ", totalDistance=" + totalDistance +
                ", totalWalks=" + totalWalks +
                '}';
    }

    public static void main(String[] args) {
        WalkingData data = new WalkingData();
        LocalDateTime now = LocalDateTime.now();
        for (int days=0; days<10;days++){
            int dist = 100 + days*10;
            LocalDateTime endTime = now.minus(days, ChronoUnit.DAYS);
            LocalDateTime startTime = endTime.minus(2, ChronoUnit.HOURS);
            if (dist == 170){
                System.out.println(startTime);
            }
            data.inputWalk(dist, startTime, endTime);
        }
        data.inputWalk();
        System.out.println(data);
        System.out.println(data.getAverageDistanceLastWeek());
        System.out.println(data.getTotalDistanceLastWeek());
        System.out.println(data.getTotalWalksLastWeek());
        System.out.println(data.getTotalDistanceLastWeekInDays());
        System.out.println(data.getTotalWalksLastWeekInDays());
        System.out.println(data.getAverageDistanceLastWeekInDays());
        System.out.println(data.getDataLastWeekInDays());
    }
}
