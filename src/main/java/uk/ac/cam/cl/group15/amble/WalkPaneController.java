package uk.ac.cam.cl.group15.amble;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

import com.sothawo.mapjfx.*;
import javafx.scene.paint.Color;

import java.util.List;

public class WalkPaneController {
    @FXML
    public StackPane selectorStack;
    @FXML
    public VBox walkSelector;
    @FXML
    public VBox durationSelector;
    @FXML
    public Label durationOneTimeSummary;
    @FXML
    public Label durationTwoTimeSummary;
    @FXML
    public Label durationThreeTimeSummary;
    @FXML
    public Label specificTimeSummaryOne;
    @FXML
    public Label specificTimeSummaryTwo;
    @FXML
    public Label specificTimeSummaryThree;
    @FXML
    public BorderPane mapContainer;
    @FXML
    public Button mapLeftArrow;
    @FXML
    public Button mapRightArrow;
    public Label mapTime;
    public Label mapLocation;
    public Label mapDistance;

    private List<Label> specificLabels;
    private MainController mainController;

    private List<Route> routes;
    private CoordinateLine currentLine;
    private int currentIdx;

    private static final int ZOOM_DEFAULT = 14;

    @FXML
    private MapView mapView;

    public void setMainController(MainController mainController) {
        this.mainController = mainController;
    }

    public void postInit() {
        // TODO: Remove this comment for demo
        initMapAndControls(Projection.WEB_MERCATOR);

        try {
            WalkTime halfHourTime = TimeSelector.chooseTime(mainController.forecast, 30, mainController.weatherPref, mainController.timePref);
            WalkTime oneHourTime = TimeSelector.chooseTime(mainController.forecast, 60, mainController.weatherPref, mainController.timePref);
            WalkTime twoHourTime = TimeSelector.chooseTime(mainController.forecast, 120, mainController.weatherPref, mainController.timePref);

            durationOneTimeSummary.setText(String.valueOf(halfHourTime));
            durationTwoTimeSummary.setText(String.valueOf(oneHourTime));
            durationThreeTimeSummary.setText(String.valueOf(twoHourTime));
        }
        catch (InvalidTimeException e){
            durationOneTimeSummary.setText("Sorry no available times :(");
            durationTwoTimeSummary.setText("Sorry no available times :(");
            durationThreeTimeSummary.setText("Sorry no available times :(");
        }

        specificLabels = List.of(specificTimeSummaryOne, specificTimeSummaryTwo, specificTimeSummaryThree);
        mapContainer.setPadding(
                new Insets(15, -14, -38, -14)
        );

        routes = CountyFactory.buildCounty("Cambridgeshire", 5).getRoutes();
        mapLeftArrow.setOnAction(e -> displayPrevRoute());
        mapRightArrow.setOnAction(e -> displayNextRoute());
    }

    public void onPreferenceChange(){
        //TODO: should recalculate all the walk times here
        try {
            WalkTime halfHourTime = TimeSelector.chooseTime(mainController.forecast, 30, mainController.weatherPref, mainController.timePref);
            WalkTime oneHourTime = TimeSelector.chooseTime(mainController.forecast, 60, mainController.weatherPref, mainController.timePref);
            WalkTime twoHourTime = TimeSelector.chooseTime(mainController.forecast, 120, mainController.weatherPref, mainController.timePref);

            durationOneTimeSummary.setText(String.valueOf(halfHourTime));
            durationTwoTimeSummary.setText(String.valueOf(oneHourTime));
            durationThreeTimeSummary.setText(String.valueOf(twoHourTime));
        }
        catch (InvalidTimeException e){
            durationOneTimeSummary.setText("Sorry no available times :(");
            durationTwoTimeSummary.setText("Sorry no available times :(");
            durationThreeTimeSummary.setText("Sorry no available times :(");
        }
    }

    @FXML
    public void initMapAndControls(Projection projection) {

        mapView.initialize(Configuration.builder()
                .projection(projection)
                .showZoomControls(false)
                .build());

        mapView.initializedProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue) {
                currentIdx = -1;
                displayNextRoute();
            }
        });
    }

    private void displayNextRoute() {
        currentIdx = (currentIdx + 1) % routes.size();
        updateRoute(routes.get(currentIdx));
    }

    private void displayPrevRoute() {
        if (currentIdx == 0) {
            currentIdx = routes.size() - 1;
        } else {
            currentIdx--;
        }
        updateRoute(routes.get(currentIdx));
    }

    private void updateRoute(Route route) {
        if (currentLine != null) {
            mapView.removeCoordinateLine(currentLine);
        }
        mapView.setZoom(ZOOM_DEFAULT);
        currentLine = route.getCoordinateLine().setColor(Color.RED).setVisible(true);
        // nothing to see here
        mapView.setCenter(currentLine.getCoordinateStream().findFirst().get());
        mapView.addCoordinateLine(currentLine);
        mapTime.setText(route.getDistance() * 12 + " minutes");
        mapLocation.setText(route.getName());
        mapDistance.setText(route.getDistance()+ "km");
    }

    public void onReturnButtonClicked(ActionEvent actionEvent) {
        selectorStack.getChildren().removeAll(durationSelector);
        selectorStack.getChildren().addAll(durationSelector);
    }

    private void switchToWalkSelector(ActionEvent actionEvent) {
        selectorStack.getChildren().removeAll(walkSelector);
        selectorStack.getChildren().addAll(walkSelector);
    }

    private void populateSubPanels(ActionEvent actionEvent, int duration) {
        try {
            List<WalkTime> times = TimeSelector.chooseTime(mainController.forecast, duration, 3,
                    mainController.weatherPref, mainController.timePref);
            for (int i = 0; i < 3; i++) {
                specificLabels.get(i).setText(String.valueOf(times.get(i)));
            }
        }
        catch(InvalidTimeException e){
            for (int i = 0; i < 3; i++) {
                specificLabels.get(i).setText(":( No available times");
            }
        }
    }

    public void onDurationOneButtonClicked(ActionEvent actionEvent) {
        populateSubPanels(actionEvent, 30);
        switchToWalkSelector(actionEvent);
    }

    public void onDurationTwoButtonClicked(ActionEvent actionEvent) {
        populateSubPanels(actionEvent, 60);
        switchToWalkSelector(actionEvent);
    }

    public void onDurationThreeButtonClicked(ActionEvent actionEvent) {
        populateSubPanels(actionEvent, 120);
        switchToWalkSelector(actionEvent);
    }
}
