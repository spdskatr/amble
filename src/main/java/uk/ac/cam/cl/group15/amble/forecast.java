package uk.ac.cam.cl.group15.amble;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Map;

public class forecast {
    public static ArrayList<SimpleWeather> getForecast(){
        ImportCurrentWeatherData importData = new ImportCurrentWeatherData();
        ArrayList<SimpleWeather> today = new  ArrayList<>();
        Map<LocalDateTime,ImportCurrentWeatherData> forecast = importData.getFutureForecast("Cambridge, UK");
        int count = 0;
        for(Map.Entry<LocalDateTime,ImportCurrentWeatherData> f: forecast.entrySet()){
            if(count == 24){
                break;
            }
            String weatherDesc = f.getValue().getWeatherDescription().toLowerCase();
            String weatherName;
            if(weatherDesc.contains("thunderstorm")|| weatherDesc.equals("squalls")){
                weatherName = "ThunderStorm";
            }
            else if(weatherDesc.contains("drizzle")){
                weatherName = "Light Rain";
            }
            else if(weatherDesc.contains("rain")){
                if(weatherDesc.equals("light rain")){
                    weatherName = "Light Rain";
                }
                else{
                    weatherName = "Rain";
                }
            }
            else if(weatherDesc.contains("snow")|| weatherDesc.contains("sleet")){
                weatherName = "Snow";
            }
            else if(weatherDesc.equals("clear sky") || weatherDesc.equals("few clouds")){
                weatherName = "Sunny";
            }
            else if(weatherDesc.equals("scattered clouds")){
                weatherName = "Partly Cloudy";
            }
            else if(weatherDesc.contains("clouds")){
                weatherName = "Cloudy";
            }
            else if(weatherDesc.equals("tornado")|| weatherDesc.equals("volcanic ash")){
                weatherName = "Danger";
            }
            else if(weatherDesc.equals("mist") || weatherDesc.equals("fog")){
                weatherName = "Mist";
            }
            else{
                throw new RuntimeException("Unknown Weather Inputted");
            }
            SimpleWeather weather = new SimpleWeather(weatherName, f.getKey().getHour());
            today.add(weather);
            count++;
        }
        return today;
    }
}
