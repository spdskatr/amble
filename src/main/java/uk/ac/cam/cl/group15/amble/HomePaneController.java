package uk.ac.cam.cl.group15.amble;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;

import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;

import java.time.format.DateTimeFormatter;
import java.util.*;

public class HomePaneController {
    @FXML
    private ImageView weatherIcon;
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
    private Label day1Forecast, day2Forecast, day3Forecast, day4Forecast, day5Forecast;


    //the following are the labels for the hour by hour future forecast
    //it will include the temperature and the weather description
    @FXML
    private Label hour1Forecast, hour2Forecast, hour3Forecast, hour4Forecast, hour5Forecast, hour6Forecast, hour7Forecast, hour8Forecast, hour9Forecast;
    @FXML
    private Label hour10Forecast, hour11Forecast, hour12Forecast, hour13Forecast, hour14Forecast, hour15Forecast, hour16Forecast, hour17Forecast, hour18Forecast;
    @FXML
    private Label hour19Forecast, hour20Forecast, hour21Forecast, hour22Forecast, hour23Forecast, hour24Forecast;

    @FXML
    private ImageView imgHour1, imgHour2, imgHour3, imgHour4, imgHour5, imgHour6, imgHour7, imgHour8, imgHour9, imgHour10, imgHour11, imgHour12, imgHour13, imgHour14;
    @FXML
    private ImageView imgHour15, imgHour16, imgHour17, imgHour18, imgHour19, imgHour20, imgHour21, imgHour22, imgHour23, imgHour24;

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

    //used so that the walk time text will be changed when preferences change
    public void onPreferenceChange(){
        try {
            WalkTime idealWalk = WalkingAlgorithms.DailyIdealWalk(mainController);
            walkTime.setText(idealWalk.startTime + " - " + idealWalk.endTime);
        }
        catch(InvalidTimeException e){
            walkTime.setText("Oops! No Times available that match your preferences. :(");
        }
    }

    //function that when given the LocalDateTime will produce the day and time int the form Monday 22:00
    public String timeFormatter(LocalDateTime time){
        String day = capitalizeFirstLetter(time.getDayOfWeek().name());
        //LocalDateTime time2 = LocalDateTime.parse(localTimeString, DateTimeFormatter.ISO_DATE_TIME);
        String strTime = time.format(DateTimeFormatter.ofPattern("HH:mm"));
        return (day + strTime);
    }

    public void postinit() {
        onPreferenceChange();

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

        Image img = new Image(HelloApplication.class.getResource(translateWeatherIconToBigIcon(icwd.getWeatherIconId())).toString(), 300, 300, false, false);
        weatherIcon.setImage(img);


        Map<DayOfWeek, double[]> forecast5Days = icwd.getForecast5Days("Cambridge, UK");
        List<DayOfWeek> listKeys = new ArrayList<>(forecast5Days.keySet());
        day1Forecast.setText(capitalizeFirstLetter(listKeys.get(0).name()) + " \t\tL: " + Double.toString(forecast5Days.get(listKeys.get(0))[0]) + "°" + "  H: " + Double.toString(forecast5Days.get(listKeys.get(0))[1]) + "°");
        day2Forecast.setText(capitalizeFirstLetter(listKeys.get(1).name()) + " \t\tL: " + Double.toString(forecast5Days.get(listKeys.get(1))[0]) + "°" + "  H: " + Double.toString(forecast5Days.get(listKeys.get(1))[1]) + "°");
        day3Forecast.setText(capitalizeFirstLetter(listKeys.get(2).name()) + " \t\tL: " + Double.toString(forecast5Days.get(listKeys.get(2))[0]) + "°" + "  H: " + Double.toString(forecast5Days.get(listKeys.get(2))[1]) + "°");
        day4Forecast.setText(capitalizeFirstLetter(listKeys.get(3).name()) + " \t\tL: " + Double.toString(forecast5Days.get(listKeys.get(3))[0]) + "°" + "  H: " + Double.toString(forecast5Days.get(listKeys.get(3))[1]) + "°");
        day5Forecast.setText(capitalizeFirstLetter(listKeys.get(4).name()) + " \t\tL: " + Double.toString(forecast5Days.get(listKeys.get(4))[0]) + "°" + "  H: " + Double.toString(forecast5Days.get(listKeys.get(4))[1]) + "°");

        Map<LocalDateTime, ImportCurrentWeatherData> hourByHourForecast = icwd.getFutureForecast("Cambridge, UK");
        List<LocalDateTime> keyList = new ArrayList<>(hourByHourForecast.keySet());
        Collections.sort(keyList);
        hour1Forecast.setText( timeFormatter(keyList.get(0)) + " :\t\t" + Double.toString(hourByHourForecast.get(keyList.get(0)).getCurrentTemp()) + "°");
        hour2Forecast.setText( timeFormatter(keyList.get(1)) + " :\t\t" + Double.toString(hourByHourForecast.get(keyList.get(1)).getCurrentTemp()) + "°");
        hour3Forecast.setText( timeFormatter(keyList.get(2)) + " :\t\t" + Double.toString(hourByHourForecast.get(keyList.get(2)).getCurrentTemp()) + "°");
        hour4Forecast.setText( timeFormatter(keyList.get(3)) + " :\t\t" + Double.toString(hourByHourForecast.get(keyList.get(3)).getCurrentTemp()) + "°");
        hour5Forecast.setText( timeFormatter(keyList.get(4)) + " :\t\t" + Double.toString(hourByHourForecast.get(keyList.get(4)).getCurrentTemp()) + "°");
        hour6Forecast.setText( timeFormatter(keyList.get(5)) + " :\t\t" + Double.toString(hourByHourForecast.get(keyList.get(5)).getCurrentTemp()) + "°");
        hour7Forecast.setText( timeFormatter(keyList.get(6)) + " :\t\t" + Double.toString(hourByHourForecast.get(keyList.get(6)).getCurrentTemp()) + "°");
        hour8Forecast.setText( timeFormatter(keyList.get(7)) + " :\t\t" + Double.toString(hourByHourForecast.get(keyList.get(7)).getCurrentTemp()) + "°");
        hour9Forecast.setText( timeFormatter(keyList.get(8)) + " :\t\t" + Double.toString(hourByHourForecast.get(keyList.get(8)).getCurrentTemp()) + "°");
        hour10Forecast.setText( timeFormatter(keyList.get(9)) + " :\t\t" + Double.toString(hourByHourForecast.get(keyList.get(9)).getCurrentTemp()) + "°");
        hour11Forecast.setText( timeFormatter(keyList.get(10)) + " :\t\t" + Double.toString(hourByHourForecast.get(keyList.get(10)).getCurrentTemp()) + "°");
        hour12Forecast.setText( timeFormatter(keyList.get(11)) + " :\t\t" + Double.toString(hourByHourForecast.get(keyList.get(11)).getCurrentTemp()) + "°");
        hour13Forecast.setText( timeFormatter(keyList.get(12)) + " :\t\t" + Double.toString(hourByHourForecast.get(keyList.get(12)).getCurrentTemp()) + "°");
        hour14Forecast.setText( timeFormatter(keyList.get(13)) + " :\t\t" + Double.toString(hourByHourForecast.get(keyList.get(13)).getCurrentTemp()) + "°");
        hour15Forecast.setText( timeFormatter(keyList.get(14)) + " :\t\t" + Double.toString(hourByHourForecast.get(keyList.get(14)).getCurrentTemp()) + "°");
        hour16Forecast.setText( timeFormatter(keyList.get(15)) + " :\t\t" + Double.toString(hourByHourForecast.get(keyList.get(15)).getCurrentTemp()) + "°");
        hour17Forecast.setText( timeFormatter(keyList.get(16)) + " :\t\t" + Double.toString(hourByHourForecast.get(keyList.get(16)).getCurrentTemp()) + "°");
        hour18Forecast.setText( timeFormatter(keyList.get(17)) + " :\t\t" + Double.toString(hourByHourForecast.get(keyList.get(17)).getCurrentTemp()) + "°");
        hour19Forecast.setText( timeFormatter(keyList.get(18)) + " :\t\t" + Double.toString(hourByHourForecast.get(keyList.get(18)).getCurrentTemp()) + "°");
        hour20Forecast.setText( timeFormatter(keyList.get(19)) + " :\t\t" + Double.toString(hourByHourForecast.get(keyList.get(19)).getCurrentTemp()) + "°");
        hour21Forecast.setText( timeFormatter(keyList.get(20)) + " :\t\t" + Double.toString(hourByHourForecast.get(keyList.get(20)).getCurrentTemp()) + "°");
        hour22Forecast.setText( timeFormatter(keyList.get(21)) + " :\t\t" + Double.toString(hourByHourForecast.get(keyList.get(21)).getCurrentTemp()) + "°");
        hour23Forecast.setText( timeFormatter(keyList.get(22)) + " :\t\t" + Double.toString(hourByHourForecast.get(keyList.get(22)).getCurrentTemp()) + "°");
        hour24Forecast.setText( timeFormatter(keyList.get(23)) + " :\t\t" + Double.toString(hourByHourForecast.get(keyList.get(23)).getCurrentTemp()) + "°");

        imgHour1.setImage(new Image("http://openweathermap.org/img/wn/" + hourByHourForecast.get(keyList.get(0)).getWeatherIconId() + "@2x.png"));
        imgHour2.setImage(new Image("http://openweathermap.org/img/wn/" + hourByHourForecast.get(keyList.get(1)).getWeatherIconId() + "@2x.png"));
        imgHour3.setImage(new Image("http://openweathermap.org/img/wn/" + hourByHourForecast.get(keyList.get(2)).getWeatherIconId() + "@2x.png"));
        imgHour4.setImage(new Image("http://openweathermap.org/img/wn/" + hourByHourForecast.get(keyList.get(3)).getWeatherIconId() + "@2x.png"));
        imgHour5.setImage(new Image("http://openweathermap.org/img/wn/" + hourByHourForecast.get(keyList.get(4)).getWeatherIconId() + "@2x.png"));
        imgHour6.setImage(new Image("http://openweathermap.org/img/wn/" + hourByHourForecast.get(keyList.get(5)).getWeatherIconId() + "@2x.png"));
        imgHour7.setImage(new Image("http://openweathermap.org/img/wn/" + hourByHourForecast.get(keyList.get(6)).getWeatherIconId() + "@2x.png"));
        imgHour8.setImage(new Image("http://openweathermap.org/img/wn/" + hourByHourForecast.get(keyList.get(7)).getWeatherIconId() + "@2x.png"));
        imgHour9.setImage(new Image("http://openweathermap.org/img/wn/" + hourByHourForecast.get(keyList.get(8)).getWeatherIconId() + "@2x.png"));
        imgHour10.setImage(new Image("http://openweathermap.org/img/wn/" + hourByHourForecast.get(keyList.get(9)).getWeatherIconId() + "@2x.png"));
        imgHour11.setImage(new Image("http://openweathermap.org/img/wn/" + hourByHourForecast.get(keyList.get(10)).getWeatherIconId() + "@2x.png"));
        imgHour12.setImage(new Image("http://openweathermap.org/img/wn/" + hourByHourForecast.get(keyList.get(11)).getWeatherIconId() + "@2x.png"));
        imgHour13.setImage(new Image("http://openweathermap.org/img/wn/" + hourByHourForecast.get(keyList.get(12)).getWeatherIconId() + "@2x.png"));
        imgHour14.setImage(new Image("http://openweathermap.org/img/wn/" + hourByHourForecast.get(keyList.get(13)).getWeatherIconId() + "@2x.png"));
        imgHour15.setImage(new Image("http://openweathermap.org/img/wn/" + hourByHourForecast.get(keyList.get(14)).getWeatherIconId() + "@2x.png"));
        imgHour16.setImage(new Image("http://openweathermap.org/img/wn/" + hourByHourForecast.get(keyList.get(15)).getWeatherIconId() + "@2x.png"));
        imgHour17.setImage(new Image("http://openweathermap.org/img/wn/" + hourByHourForecast.get(keyList.get(16)).getWeatherIconId() + "@2x.png"));
        imgHour18.setImage(new Image("http://openweathermap.org/img/wn/" + hourByHourForecast.get(keyList.get(17)).getWeatherIconId() + "@2x.png"));
        imgHour19.setImage(new Image("http://openweathermap.org/img/wn/" + hourByHourForecast.get(keyList.get(18)).getWeatherIconId() + "@2x.png"));
        imgHour20.setImage(new Image("http://openweathermap.org/img/wn/" + hourByHourForecast.get(keyList.get(19)).getWeatherIconId() + "@2x.png"));
        imgHour21.setImage(new Image("http://openweathermap.org/img/wn/" + hourByHourForecast.get(keyList.get(20)).getWeatherIconId() + "@2x.png"));
        imgHour22.setImage(new Image("http://openweathermap.org/img/wn/" + hourByHourForecast.get(keyList.get(21)).getWeatherIconId() + "@2x.png"));
        imgHour23.setImage(new Image("http://openweathermap.org/img/wn/" + hourByHourForecast.get(keyList.get(22)).getWeatherIconId() + "@2x.png"));
        imgHour24.setImage(new Image("http://openweathermap.org/img/wn/" + hourByHourForecast.get(keyList.get(23)).getWeatherIconId() + "@2x.png"));


    }

    private String translateWeatherIconToBigIcon(String weatherIconId) {
        switch (weatherIconId.substring(0, 2)) {
            case "01":
                // Sunny
                return "sunny.png";
            case "02":
                // Few clouds
            case "04":
                // Broken Clouds
                return "partly_cloudy.png";
            case "03":
                // Cloudy
                return "cloudy.png";
            case "09":
                // Shower Rain
                return "light_rain.png";
            case "10":
                // Rain
                return "rain.png";
            case "11":
                // Thunderstorm
                return "storm.png";
            case "13":
                // Snow
                return "snow.png";
            case "50":
                // Mist
                return "mist.png";
        }
        return "danger.png";
    }
}
