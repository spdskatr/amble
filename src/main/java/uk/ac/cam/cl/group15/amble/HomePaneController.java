package uk.ac.cam.cl.group15.amble;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public class HomePaneController {
    @FXML
    private Label welcomeText;

    @FXML
    private VBox mainView;
    private MainController mainController;

    public void setMainController(MainController mainController) {
        this.mainController = mainController;
    }

    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");
    }

    @FXML
    public void onTakeWalkButtonClick(ActionEvent actionEvent) {
        mainController.switchPaneTo(MainController.PaneType.WALK);
    }

    @FXML
    public void onEnterSettingsButtonPressed(ActionEvent actionEvent) {
        mainController.enterSettings();
    }
}
