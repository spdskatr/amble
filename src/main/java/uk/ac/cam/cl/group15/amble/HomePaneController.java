package uk.ac.cam.cl.group15.amble;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.util.ResourceBundle;

public class HomePaneController implements Initializable {
    @FXML
    private Label welcomeText;
    @FXML
    private Label walkTime;

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

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        walkTime.setText("10:00 - 11:00"); //TODO: Make this output result of TimeSelector.ChooseTime
    }
}
