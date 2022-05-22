package uk.ac.cam.cl.group15.amble;

import javafx.fxml.FXML;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Map;

public class StatsPaneController {

    final static WalkingData DATA = WalkingData.sampleData();
    final static Map<Integer, String> DAYS = Map.of(0, "Monday", 1, "Tuesday",
            2, "Wednesday", 3, "Thursday", 4, "Friday",
            5, "Saturday", 6, "Sunday");

    @FXML
    private BarChart<CategoryAxis, NumberAxis> barChartTotalDistance;
    private MainController mainController;

    public void setMainController(MainController mainController) {
        this.mainController = mainController;
    }

    public void postInit() {
        XYChart.Series series1 = new XYChart.Series();
        series1.setName("Distance (km)");
        int currentDay = LocalDateTime.now().getDayOfWeek().getValue() - 1;
        ArrayList<Double> distances = DATA.getTotalDistanceLastWeekInDays();
        for (int i=0; i<7; i++) {
            currentDay = (currentDay + 1) % 7;
            series1.getData().add(new XYChart.Data<>(DAYS.get(currentDay), distances.get(currentDay)));
        }
        barChartTotalDistance.getData().addAll(series1);
    }
}
