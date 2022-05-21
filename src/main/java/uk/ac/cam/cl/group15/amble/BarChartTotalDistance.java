package uk.ac.cam.cl.group15.amble;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.stage.Stage;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BarChartTotalDistance extends Application {
    final static WalkingData data = WalkingData.sampleData();
    final static Map<Integer, String> days = Map.of(0, "Monday", 1, "Tuesday",
            2, "Wednesday", 3, "Thursday", 4, "Friday",
            5, "Saturday", 6, "Sunday");

    @Override public void start(Stage stage) {
        stage.setTitle("Bar Chart Sample");
        final CategoryAxis xAxis = new CategoryAxis();
        final NumberAxis yAxis = new NumberAxis();
        final BarChart<String,Number> bc =
                new BarChart<String,Number>(xAxis,yAxis);
        bc.setTitle("Weekly Summary");
        xAxis.setLabel("Day");
        yAxis.setLabel("Total Distance");

        XYChart.Series series1 = new XYChart.Series();
        series1.setName("Total Distance");
//        XYChart.Series series2 = new XYChart.Series();
//        series2.setName("Total Walks");
//        XYChart.Series series3 = new XYChart.Series();
//        series3.setName("Average Distance");
        int currentDay = LocalDateTime.now().getDayOfWeek().getValue() - 1;
        ArrayList<Double> distances = data.getTotalDistanceLastWeekInDays();
//        ArrayList<Double> walks = data.getTotalWalksLastWeekInDays();
//        ArrayList<Double> averages = data.getAverageDistanceLastWeekInDays();
        for (int i=0; i<7; i++) {
            currentDay = (currentDay + 1) % 7;
            series1.getData().add(new XYChart.Data<>(days.get(currentDay), distances.get(currentDay)));
//            series2.getData().add(new XYChart.Data<>(days.get(currentDay), walks.get(currentDay)));
//            series3.getData().add(new XYChart.Data<>(days.get(currentDay), averages.get(currentDay)));
        }

        Scene scene  = new Scene(bc,800,600);
//        bc.getData().addAll(series1, series2, series3);
        bc.getData().addAll(series1);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}

