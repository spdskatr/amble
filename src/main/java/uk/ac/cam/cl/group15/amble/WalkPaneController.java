package uk.ac.cam.cl.group15.amble;

import com.sothawo.mapjfx.*;
import javafx.fxml.FXML;
import javafx.scene.paint.Color;

import java.util.List;

public class WalkPaneController {
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
}
