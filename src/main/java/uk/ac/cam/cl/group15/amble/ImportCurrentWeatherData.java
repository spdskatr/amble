package uk.ac.cam.cl.group15.amble;
import uk.ac.cam.cl.group15.amble.openweathermapapi.OpenWeatherMapClient;
import uk.ac.cam.cl.group15.amble.openweathermapapi.enums.Language;
import uk.ac.cam.cl.group15.amble.openweathermapapi.enums.UnitSystem;
import uk.ac.cam.cl.group15.amble.openweathermapapi.model.onecall.current.CurrentWeatherData;
import uk.ac.cam.cl.group15.amble.openweathermapapi.model.onecall.current.Hourly;
import uk.ac.cam.cl.group15.amble.openweathermapapi.model.weather.Weather;
import uk.ac.cam.cl.group15.amble.openweathermapapi.model.weather.Wind;
import uk.ac.cam.cl.group15.amble.openweathermapapi.model.*;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

public class ImportCurrentWeatherData {

    //private variables for all of the relevant details
    private static OpenWeatherMapClient client;
    private double currentTemp, maxTemp, minTemp, tempFeelsLike, airPressure, windSpeed, humidity, probOfRain;
    private String weatherDescription, placeName;

    //this map is used to store the forecast
    //the ImportCurrentWeatherData object stores all of the relevant details for the future forecasts
    private Map<LocalDateTime, ImportCurrentWeatherData> forecast;

    //constructor
    ImportCurrentWeatherData(){
        retrieveApiKey();
    }

    //gets the api key for the weather api
    public void retrieveApiKey() {
        String apiKey = "426ed4145dc5b025f4a71e5d11245c83";
        System.out.println(apiKey);
        client = new OpenWeatherMapClient(apiKey);
    }

    //given the place name in the form 'Cambridge, UK'
    //it will set the relevant weather details using the setters which can then be accessed at a later date
    public void getCurrentData(String placeName){

        //this is from the api and will get the weather
        //all of the details is stored in the object
        final Weather weather = client
                .currentWeather()
                .single()
                .byCityName(placeName)
                .language(Language.ENGLISH)
                .unitSystem(UnitSystem.METRIC)
                .retrieve()
                .asJava();

        //System.out.println("CurrentTemp " + weather.getTemperature().getMaxTemperature() + " at " + weather.getLocation().getName());

        //these two blocks of code below set the attributes
        Temperature temp = weather.getTemperature();
        Humidity humid = weather.getHumidity();
        WeatherState ws = weather.getWeatherState();
        AtmosphericPressure atm = weather.getAtmosphericPressure();
        Wind wind = weather.getWind();

        setCurrentTemp(temp.getValue());
        setAirPressure(atm.getValue());
        setMaxTemp(temp.getMaxTemperature());
        setMinTemp(temp.getMinTemperature());
        setPlaceName(placeName);
        setWeatherDescription(ws.getDescription());
        setTempFeelsLike(temp.getFeelsLike());
        setHumidity(humid.getValue());
        setWindSpeed(wind.getSpeed());

    }

    //given the place name in the form 'Cambridge, UK' it will give the hourly forecast for the next two days
    //it does this by setting the map 'forecast' which is a map from the time to an object
    //this object stores the relevant details for the forecast
    public Map<LocalDateTime, ImportCurrentWeatherData> getFutureForecast(String placeName){
        //creating the map
        Map<LocalDateTime, ImportCurrentWeatherData> currentForecastMap = new HashMap<>();
        double[] coordinates = new double[] {52.205276, 0.119167}; //getting the coordinates

        //this is the call that will get the forecast - it will only work with longitude/latitude
        //and not the actual place name
        final CurrentWeatherData forecast = client
                .oneCall()
                .current()
                .byCoordinate(Coordinate.of(coordinates[0], coordinates[1]))
                .language(Language.ENGLISH)
                .unitSystem(UnitSystem.METRIC)
                .retrieve()
                .asJava();

        //this iterates over the 'hourly' objects which stores the weather details for each hour of the forecast
        for (Hourly hr : forecast.getHourlyList()) {
            ImportCurrentWeatherData tempObject = new ImportCurrentWeatherData();
            LocalDateTime time = hr.getForecastTime();

            tempObject.setAirPressure(hr.getAtmosphericPressure().getSeaLevelValue());
            tempObject.setCurrentTemp(hr.getTemperature().getValue());
            tempObject.setWindSpeed(hr.getWind().getSpeed());
            tempObject.setHumidity(hr.getHumidity().getValue());
            tempObject.setWeatherDescription(hr.getWeatherState().getDescription());
            tempObject.setProbOfRain(hr.getProbabilityOfPrecipitation());

            System.out.println("Time " + time);
            System.out.println("Temp " + hr.getTemperature().getValue());

            currentForecastMap.put(time, tempObject);
        }
        return currentForecastMap;

    }

    //SETTERS
    public void setAirPressure(double airPressure) {
        this.airPressure = airPressure;
    }
    public void setCurrentTemp(double currentTemp) {
        this.currentTemp = currentTemp;
    }
    public void setHumidity(double humidity) {
        this.humidity = humidity;
    }
    public void setMaxTemp(double maxTemp) {
        this.maxTemp = maxTemp;
    }
    public void setMinTemp(double minTemp) {
        this.minTemp = minTemp;
    }
    public void setTempFeelsLike(double tempFeelsLike) {
        this.tempFeelsLike = tempFeelsLike;
    }
    public void setWeatherDescription(String weatherDescription) {
        this.weatherDescription = weatherDescription;
    }
    public void setWindSpeed(double windSpeed) {
        this.windSpeed = windSpeed;
    }
    public void setPlaceName(String placeName) {
        this.placeName = placeName;
    }
    public void setForecast(Map<LocalDateTime, ImportCurrentWeatherData> forecast) {
        this.forecast = forecast;
    }
    public void setProbOfRain(double probOfRain) {
        this.probOfRain = probOfRain;
    }

    //GETTERS
    public double getAirPressure() {
        return airPressure;
    }
    public double getCurrentTemp() {
        return currentTemp;
    }
    public double getMaxTemp() {
        return maxTemp;
    }
    public double getHumidity() {
        return humidity;
    }
    public double getMinTemp() {
        return minTemp;
    }
    public double getTempFeelsLike() {
        return tempFeelsLike;
    }
    public double getWindSpeed() {
        return windSpeed;
    }
    public double getProbOfRain() {
        return probOfRain;
    }
    public String getWeatherDescription() {
        return weatherDescription;
    }
    public String getPlaceName() {
        return placeName;
    }
    public Map<LocalDateTime, ImportCurrentWeatherData> getForecast() {
        return forecast;
    }

    public static void main(String[] args) {
        ImportCurrentWeatherData icwd = new ImportCurrentWeatherData();
        icwd.retrieveApiKey();
        icwd.getFutureForecast("Cambridge, UK");
        icwd.getCurrentData("Cambridge, UK");
    }
}
