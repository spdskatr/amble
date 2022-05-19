package uk.ac.cam.cl.group15.amble;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;

public class SettingsPaneController {
    private MainController mainController;

    public void setMainController(MainController mainController) {
        this.mainController = mainController;
    }

    @FXML
    public void onExitSettingsButtonPressed(ActionEvent actionEvent) {
        mainController.exitSettings();
    }

    @FXML
    public void onConfigureTimePressed(ActionEvent actionEvent){
        System.out.println("hewwo");
    }
}
