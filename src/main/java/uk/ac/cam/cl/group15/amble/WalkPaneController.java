package uk.ac.cam.cl.group15.amble;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

import com.sothawo.mapjfx.*;
import javafx.fxml.FXML;
import javafx.scene.paint.Color;

import java.util.List;

public class WalkPaneController {
    @FXML
    public StackPane selectorStack;
    @FXML
    public VBox walkSelector;
    @FXML
    public VBox durationSelector;
    private MainController mainController;

    private static final int ZOOM_DEFAULT = 14;

    @FXML
    private MapView mapView;

    public void setMainController(MainController mainController) {
        this.mainController = mainController;
    }

    public void postInit() {
        initMapAndControls(Projection.WEB_MERCATOR);
    }

    @FXML
    public void initMapAndControls(Projection projection) {

        County bedfordshire = CountyFactory.buildCounty("Bedfordshire");

        List<Route> routes = bedfordshire.getBoundedRoutes(2, 8);

        final CoordinateLine line = routes.get(6).getCoordinateLine().setColor(Color.MAGENTA).setVisible(true);

        mapView.initialize(Configuration.builder()
                .projection(projection)
                .showZoomControls(false)
                .build());

        mapView.initializedProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue) {
                addLine(line);
            }
        });
    }

    private void addLine(CoordinateLine line) {
        mapView.setZoom(ZOOM_DEFAULT);
        mapView.setCenter(line.getCoordinateStream().findFirst().get());
        mapView.addCoordinateLine(line);
    }

    public void onReturnButtonClicked(ActionEvent actionEvent) {
        selectorStack.getChildren().removeAll(durationSelector);
        selectorStack.getChildren().addAll(durationSelector);
    }

    private void switchToWalkSelector(ActionEvent actionEvent) {
        selectorStack.getChildren().removeAll(walkSelector);
        selectorStack.getChildren().addAll(walkSelector);
    }

    public void onDurationOneButtonClicked(ActionEvent actionEvent) {
        switchToWalkSelector(actionEvent);
    }
}
