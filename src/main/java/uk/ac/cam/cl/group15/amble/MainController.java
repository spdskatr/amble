package uk.ac.cam.cl.group15.amble;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class MainController implements Initializable {

    public enum PaneType {
        STATS,
        HOME,
        WALK
    }

    // Main parts
    @FXML
    private StackPane mainContainer;
    @FXML
    private VBox settingsView;
    @FXML
    private VBox mainView;

    // Main sub-panes
    @FXML
    private StackPane paneContainer;
    @FXML
    private Node statsPane;
    @FXML
    private Node homePane;
    @FXML
    private Node walkPane;
    @FXML
    private VBox settingsPane;

    // Controllers for sub-panes
    @FXML
    private HomePaneController homePaneController;
    @FXML
    private StatsPaneController statsPaneController;
    @FXML
    private WalkPaneController walkPaneController;
    @FXML
    private SettingsPaneController settingsPaneController;

    public ArrayList<SimpleWeather> forecast;

    // Initialise by assigning to child controllers
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        homePaneController.setMainController(this);
        statsPaneController.setMainController(this);
        walkPaneController.setMainController(this);
        settingsPaneController.setMainController(this);
        forecast = uk.ac.cam.cl.group15.amble.forecast.getForecast();
        homePaneController.postinit();
        walkPaneController.postInit();
    }

    @FXML
    protected void onStatsButtonClick() {
        switchPaneTo(PaneType.STATS);
    }

    @FXML
    protected void onHomeButtonClick() {
        switchPaneTo(PaneType.HOME);
    }

    @FXML
    protected void onWalkButtonClick() {
        switchPaneTo(PaneType.WALK);
    }

    @FXML
    public void onEnterSettingsButtonPressed(ActionEvent actionEvent) {
        enterSettings();
    }

    @FXML
    public void enterSettings() {
        mainContainer.getChildren().removeAll(settingsView);
        mainContainer.getChildren().addAll(settingsView);
    }

    @FXML
    public void exitSettings() {
        mainContainer.getChildren().removeAll(mainView);
        mainContainer.getChildren().addAll(mainView);
    }

    public void switchPaneTo(PaneType type) {
        Node nodeToSwitch;
        switch (type) {
            case STATS -> nodeToSwitch = statsPane;
            case HOME -> nodeToSwitch = homePane;
            case WALK -> nodeToSwitch = walkPane;
            default -> throw new IllegalArgumentException("Unexpected value: " + type);
        }
        paneContainer.getChildren().removeAll(nodeToSwitch);
        paneContainer.getChildren().addAll(nodeToSwitch);
        // TODO: Change background colour of the navigation buttons
    }
}