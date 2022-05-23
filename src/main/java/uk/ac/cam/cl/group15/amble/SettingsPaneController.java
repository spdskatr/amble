package uk.ac.cam.cl.group15.amble;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

import java.util.ArrayList;

public class SettingsPaneController {
    private MainController mainController;

    private boolean timeMenuOpen;
    private boolean weatherMenuOpen;

    @FXML
    public StackPane timeConfig;
    @FXML
    public VBox timeBox;
    @FXML
    public StackPane weatherConfig;
    @FXML
    public VBox weatherBox;

    //Weather Preferences
    @FXML
    private ComboBox<String> sunny;
    @FXML
    private ComboBox<String> partlyCloudy;
    @FXML
    private ComboBox<String> cloudy;
    @FXML
    private ComboBox<String> lightRain;
    @FXML
    private ComboBox<String> rain;
    @FXML
    private ComboBox<String> snow;
    @FXML
    private ComboBox<String> mist;
    @FXML
    private ComboBox<String> storm;
    @FXML
    private ComboBox<String> danger;

    //Time Preferences


    public void setMainController(MainController mainController) {
        this.mainController = mainController;
    }

    //Buttons
    @FXML
    public void onExitSettingsButtonPressed(ActionEvent actionEvent) {
        mainController.exitSettings();
        mainController.onSettingsChange();
    }

    @FXML
    public void onConfigureTimePressed(ActionEvent actionEvent){
        if(timeMenuOpen){
            timeConfig.getChildren().removeAll(timeBox);
            timeMenuOpen = false;
        }
        else{
            timeConfig.getChildren().addAll(timeBox);
            timeMenuOpen = true;
        }
    }

    public void onWeatherConfigPressed(ActionEvent actionEvent) {
        if(weatherMenuOpen){
            weatherConfig.getChildren().removeAll(weatherBox);
            weatherMenuOpen = false;
        }
        else{
            weatherConfig.getChildren().addAll(weatherBox);
            weatherMenuOpen = true;
        }
    }

//Helper Functions
    public pref stringToEnum(String s){
        switch(s){
            case("Very High"): return pref.VERYHIGH;
            case ("High"): return pref.HIGH;
            case ("Medium"): return pref.MEDIUM;
            case ("Low"): return pref.LOW;
            case ("Very Low"): return pref.VERYLOW;
            default: return null;
        }
    }

    public void setUpDropDown(String prefName, ComboBox<String> box){
        box.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String oldPref, String newPref){
                mainController.weatherPref.setPreference(prefName, stringToEnum(newPref));
                System.out.println("uwu");
            }
        });
        System.out.println("Hi is this on?");
    }

//Initialisation
    public void onOpen(){
        timeMenuOpen = false;
        weatherMenuOpen = false;
        timeConfig.getChildren().removeAll(timeBox);
        weatherConfig.getChildren().removeAll(weatherBox);
    }

    public void postInit(){
        setUpDropDown("Sunny", sunny);
        setUpDropDown("Partly Cloudy", partlyCloudy);
        setUpDropDown("Cloudy", cloudy);
        setUpDropDown("Light Rain", lightRain);
        setUpDropDown("Rain", rain);
        setUpDropDown("Snow", snow);
        setUpDropDown("Mist", mist);
        setUpDropDown("Thunderstorm", storm);
        setUpDropDown("Danger", danger);
    }
}
