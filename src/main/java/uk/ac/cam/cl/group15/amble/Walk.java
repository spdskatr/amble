package uk.ac.cam.cl.group15.amble;

import java.time.*;
import java.time.format.DateTimeFormatter;


public class Walk {
    private double distance;
    private LocalDateTime start;
    private LocalDateTime end;


    Walk(){}


    Walk(double distance){
        this.distance = distance;
    }

    Walk(double distance, LocalDateTime start, LocalDateTime end){
        this.distance = distance;
        this.start = start;
        this.end = end;
    }

    void setDistance(double distance){
        this.distance = distance;
    }

    void setStartTime(LocalDateTime start){
        this.start = start;
    }

    void setEndTime(LocalDateTime end){
        this.end = end;
    }

    double getDistance(){
        return this.distance;
    }

    LocalDateTime getStartTime(){
        return this.start;
    }

    DayOfWeek getDayOfWeek(){
        return this.start.getDayOfWeek();
    }

    int getWalkTime() {
        if (start == null || end == null) {
            throw new IllegalArgumentException("Need both a start and end time to compute time");
        }
        return start.compareTo(end);
    }


    @Override
    public String toString() {
        return "Walk{" +
                "distance=" + distance +
                ", start=" + start +
                ", end=" + end +
                '}';
    }

    public static void main(String[] args) {
        Walk walk = new Walk();
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        walk.setStartTime(now);
        System.out.println(walk.getDayOfWeek());
        System.out.println(dtf.format(now));
        System.out.println(walk.getWalkTime());

        LocalTime time = LocalTime.of(23, 45);
        LocalDate date = LocalDate.of(2019, Month.NOVEMBER, 23);

        LocalDateTime combined = date.atTime(time);
        System.out.println(dtf.format(combined));
    }
}

