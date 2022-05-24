package uk.ac.cam.cl.group15.amble;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

import java.util.ArrayList;

import static uk.ac.cam.cl.group15.amble.pref.*;

public class SettingsPaneController {
    private MainController mainController;

    private boolean timeMenuOpen;
    private boolean weatherMenuOpen;
    private boolean goalOpen;

    @FXML
    public StackPane timeConfig;
    @FXML
    public VBox timeBox;
    @FXML
    public StackPane weatherConfig;
    @FXML
    public VBox weatherBox;
    @FXML
    public VBox goalBox;
    @FXML
    public StackPane goalConfig;
    @FXML
    public ComboBox<String> goalNumber;

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
    @FXML
    private ComboBox<String> zero;
    @FXML
    private ComboBox<String> one;
    @FXML
    private ComboBox<String> two;
    @FXML
    private ComboBox<String> three;
    @FXML
    private ComboBox<String> four;
    @FXML
    private ComboBox<String> five;
    @FXML
    private ComboBox<String> six;
    @FXML
    private ComboBox<String> seven;
    @FXML
    private ComboBox<String> eight;
    @FXML
    private ComboBox<String> nine;
    @FXML
    private ComboBox<String> ten;
    @FXML
    private ComboBox<String> eleven;
    @FXML
    private ComboBox<String> twelve;
    @FXML
    private ComboBox<String> thirteen;
    @FXML
    private ComboBox<String> fourteen;
    @FXML
    private ComboBox<String> fifteen;
    @FXML
    private ComboBox<String> sixteen;
    @FXML
    private ComboBox<String> seventeen;
    @FXML
    private ComboBox<String> eighteen;
    @FXML
    private ComboBox<String> nineteen;
    @FXML
    private ComboBox<String> twenty;
    @FXML
    private ComboBox<String> twentyOne;
    @FXML
    private ComboBox<String> twentyTwo;
    @FXML
    private ComboBox<String> twentyThree;

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

    public void onSetGoalPressed(ActionEvent actionEvent){
        if(goalOpen){
            goalConfig.getChildren().removeAll(goalBox);
            goalOpen = false;
        }
        else{
            goalConfig.getChildren().addAll(goalBox);
            goalOpen = true;
        }
    }

//Helper Functions
    public pref stringToEnum(String s){
        return switch (s) {
            case ("Very High") -> VERYHIGH;
            case ("High") -> HIGH;
            case ("Medium") -> MEDIUM;
            case ("Low") -> LOW;
            case ("Very Low") -> VERYLOW;
            case ("Never") -> NEVER;
            default -> null;
        };
    }

    public String enumToString(pref p){
        return switch (p) {
            case VERYHIGH -> "Very High";
            case HIGH -> "High";
            case MEDIUM -> "Medium";
            case LOW -> "Low";
            case VERYLOW -> "Very Low";
            case NEVER -> "Never";
            default -> null;
        };
    }


    public void setUpWDropDown(String prefName, ComboBox<String> box){
        box.getSelectionModel().selectedItemProperty().addListener((observableValue, oldPref, newPref) -> mainController.weatherPref.setPreference(prefName, stringToEnum(newPref)));
        box.getSelectionModel().select(enumToString(mainController.weatherPref.getPreference(prefName)));
    }

    public void setUpTDropDown(String prefName, ComboBox<String> box){
        box.getSelectionModel().selectedItemProperty().addListener((observableValue, oldPref, newPref) -> mainController.timePref.setPreference(prefName, stringToEnum(newPref)));
        box.getSelectionModel().select(enumToString(mainController.timePref.getPreference(prefName)));
    }

//Initialisation
    public void onOpen(){
        timeMenuOpen = false;
        weatherMenuOpen = false;
        goalOpen = false;
        timeConfig.getChildren().removeAll(timeBox);
        weatherConfig.getChildren().removeAll(weatherBox);
        goalConfig.getChildren().removeAll(goalBox);
    }

    public void postInit(){
        setUpWDropDown("Sunny", sunny);
        setUpWDropDown("Partly Cloudy", partlyCloudy);
        setUpWDropDown("Cloudy", cloudy);
        setUpWDropDown("Light Rain", lightRain);
        setUpWDropDown("Rain", rain);
        setUpWDropDown("Snow", snow);
        setUpWDropDown("Mist", mist);
        setUpWDropDown("Thunderstorm", storm);
        setUpWDropDown("Danger", danger);

        setUpTDropDown("0", zero);
        setUpTDropDown("1",one);
        setUpTDropDown("2", two);
        setUpTDropDown("3", three);
        setUpTDropDown("4", four);
        setUpTDropDown("5", five);
        setUpTDropDown("6", six);
        setUpTDropDown("7", seven);
        setUpTDropDown("8", eight);
        setUpTDropDown("9", nine);
        setUpTDropDown("10", ten);
        setUpTDropDown("11", eleven);
        setUpTDropDown("12", twelve);
        setUpTDropDown("13",thirteen);
        setUpTDropDown("14", fourteen);
        setUpTDropDown("15", fifteen);
        setUpTDropDown("16", sixteen);
        setUpTDropDown("17", seventeen);
        setUpTDropDown("18", eighteen);
        setUpTDropDown("19", nineteen);
        setUpTDropDown("20", twenty);
        setUpTDropDown("21", twentyOne);
        setUpTDropDown("22", twentyTwo);
        setUpTDropDown("23", twentyThree);

        goalNumber.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String s, String t1) {
                mainController.setTarget(Integer.parseInt(t1));
            }
        });
    }
}
