package uk.ac.cam.cl.group15.amble;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

public class WalkPaneController {
    @FXML
    public StackPane selectorStack;
    @FXML
    public VBox walkSelector;
    @FXML
    public VBox durationSelector;
    private MainController mainController;

    public void setMainController(MainController mainController) {
        this.mainController = mainController;
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
