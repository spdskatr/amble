package uk.ac.cam.cl.group15.amble;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;

import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;

import java.net.URL;
import java.util.*;

public class HomePaneController {
    @FXML
    private Label welcomeText;
    @FXML
    private Label walkTime;
    @FXML
    private Label weatherDescription;
    @FXML
    private Label temperature;
    //private Label placeName;
    @FXML
    private Label humidityLabel;
    @FXML
    private Label airPressure;
    @FXML
    private Label windSpeed;
    @FXML
    private Label feelsLikeLabel;
    @FXML
    private Label date;

    //the following are the labels for the forecast - the next 5 days
    @FXML
    private Label day1Forecast;
    @FXML
    private Label day2Forecast;
    @FXML
    private Label day3Forecast;
    @FXML
    private Label day4Forecast;
    @FXML
    private Label day5Forecast;

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
        str = str.toLowerCase();
        String[] splitUp = str.split("\\s");
        String answer = "";

        for (int i = 0; i < splitUp.length; i++){
            String temp = Character.toString(Character.toUpperCase(splitUp[i].charAt(0)));
            answer = temp + splitUp[i].substring(1) + " ";
        }
        return answer;
    }

    //this is a function to get the date in the form: 'Monday 21st'
    //there is no way to get the 1st 2nd 3rd... without using some sort of array or other data structure like below
    public String getDateFormatted(){
        String[] dateSuffix =
                //    0     1     2     3     4     5     6     7     8     9
                { "th", "st", "nd", "rd", "th", "th", "th", "th", "th", "th",
                        //    10    11    12    13    14    15    16    17    18    19
                        "th", "th", "th", "th", "th", "th", "th", "th", "th", "th",
                        //    20    21    22    23    24    25    26    27    28    29
                        "th", "st", "nd", "rd", "th", "th", "th", "th", "th", "th",
                        //    30    31
                        "th", "st" };

        Date date = new Date();
        SimpleDateFormat sdf  = new SimpleDateFormat("d");
        int dayNum = Integer.parseInt(sdf.format(date));
        String index = dayNum + dateSuffix[dayNum];

        String day = LocalDate.now().getDayOfWeek().name();

        String answer = capitalizeFirstLetter(day)+ index;
        return answer;
    }

    public void postinit() {
        WalkTime idealWalk =  WalkingAlgorithms.DailyIdealWalk(mainController);
        walkTime.setText(idealWalk.startTime + " - " + idealWalk.endTime); //TODO: Make this output result of TimeSelector.ChooseTime


        ImportCurrentWeatherData icwd = new ImportCurrentWeatherData();
        icwd.getCurrentData("Cambridge, UK");
        String weatherDesc = capitalizeFirstLetter(icwd.getWeatherDescription());
        weatherDescription.setText(weatherDesc);
        temperature.setText(Double.toString(icwd.getCurrentTemp()) + "°");
        date.setText(getDateFormatted());

        humidityLabel.setText("Humidity: " + Double.toString(icwd.getHumidity()) + "%");
        windSpeed.setText("Wind Speed: " + Double.toString(icwd.getWindSpeed()) + "mph");
        feelsLikeLabel.setText("Feels Like " + Double.toString(icwd.getTempFeelsLike()) + "°");
        airPressure.setText("Air Pressure " + Double.toString(icwd.getAirPressure()) + "hPa");

        Map<DayOfWeek, double[]> forecast5Days = icwd.getForecast5Days("Cambridge, UK");
        List<DayOfWeek> listKeys = new ArrayList<>(forecast5Days.keySet());
        day1Forecast.setText(capitalizeFirstLetter(listKeys.get(0).name()) + " \t\tL: " + Double.toString(forecast5Days.get(listKeys.get(0))[0]) + "°" + "  H: " + Double.toString(forecast5Days.get(listKeys.get(0))[1]) + "°");
        day2Forecast.setText(capitalizeFirstLetter(listKeys.get(1).name()) + " \t\tL: " + Double.toString(forecast5Days.get(listKeys.get(1))[0]) + "°" + "  H: " + Double.toString(forecast5Days.get(listKeys.get(1))[1]) + "°");
        day3Forecast.setText(capitalizeFirstLetter(listKeys.get(2).name()) + " \t\tL: " + Double.toString(forecast5Days.get(listKeys.get(2))[0]) + "°" + "  H: " + Double.toString(forecast5Days.get(listKeys.get(2))[1]) + "°");
        day4Forecast.setText(capitalizeFirstLetter(listKeys.get(3).name()) + " \t\tL: " + Double.toString(forecast5Days.get(listKeys.get(3))[0]) + "°" + "  H: " + Double.toString(forecast5Days.get(listKeys.get(3))[1]) + "°");
        day5Forecast.setText(capitalizeFirstLetter(listKeys.get(4).name()) + " \t\tL: " + Double.toString(forecast5Days.get(listKeys.get(4))[0]) + "°" + "  H: " + Double.toString(forecast5Days.get(listKeys.get(4))[1]) + "°");

    }
}
