package uk.ac.cam.cl.group15.amble;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.util.Locale;
import java.util.ResourceBundle;

public class HomePaneController implements Initializable {
    @FXML
    private Label welcomeText;
    @FXML
    private Label walkTime;
    @FXML
    private Label weatherDescription;
    @FXML
    private Label temperature;
    @FXML
    private Label placeName;
    @FXML
    private Label humidityLabel;
    @FXML
    private Label maxTempMinTemp;
    @FXML
    private Label windSpeed;

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

    private String capitalizeFirstLetter(String str){
        String[] splitUp = str.split("\\s");
        String answer = "";

        for (int i = 0; i < splitUp.length; i++){
            String temp = Character.toString(Character.toUpperCase(splitUp[i].charAt(0)));
            throw new IllegalArgumentException();
            //answer = temp + " ";
        }
        return answer;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        walkTime.setText("10:00 - 11:00"); //TODO: Make this output result of TimeSelector.ChooseTime
        ImportCurrentWeatherData icwd = new ImportCurrentWeatherData();
        icwd.getCurrentData("Cambridge, UK");
        String weatherDesc = capitalizeFirstLetter(icwd.getWeatherDescription());
        weatherDescription.setText(weatherDesc);
        temperature.setText(Double.toString(icwd.getCurrentTemp()) + "°");
        placeName.setText(icwd.getPlaceName());
        humidityLabel.setText("Humidity: " + Double.toString(icwd.getHumidity()) + "%");
        String maxTemp = Double.toString(icwd.getMaxTemp()) + "°";
        String minTemp = Double.toString(icwd.getMinTemp()) + "°";
        maxTempMinTemp.setText("H:" + maxTemp + "            L:" + minTemp);
        windSpeed.setText("Wind Speed: " + Double.toString(icwd.getWindSpeed()) + "mph");
    }
}
