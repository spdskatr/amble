/*
 * Copyright (c) 2021 Alexey Zinchenko
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package uk.ac.cam.cl.group15.amble.openweathermapapi.model.onecall.historical;

import uk.ac.cam.cl.group15.amble.openweathermapapi.model.Clouds;
import uk.ac.cam.cl.group15.amble.openweathermapapi.model.Humidity;
import uk.ac.cam.cl.group15.amble.openweathermapapi.model.WeatherState;
import uk.ac.cam.cl.group15.amble.openweathermapapi.model.onecall.*;

import java.time.LocalDateTime;
import java.util.Objects;

/**
 * The type Hourly historical.
 */
public class HourlyHistorical {
    private LocalDateTime forecastTime;

    private WeatherState weatherState;
    private Temperature temperature;
    private AtmosphericPressure atmosphericPressure;
    private Humidity humidity;
    private Clouds clouds;
    private Double visibilityInMetres;
    private Wind wind;
    private Rain rain;
    private Snow snow;

    /**
     * Gets forecast time.
     *
     * @return the forecast time
     */
    public LocalDateTime getForecastTime() {
        return forecastTime;
    }

    /**
     * Sets forecast time.
     *
     * @param forecastTime the forecast time
     */
    public void setForecastTime(LocalDateTime forecastTime) {
        this.forecastTime = forecastTime;
    }

    /**
     * Gets weather state.
     *
     * @return the weather state
     */
    public WeatherState getWeatherState() {
        return weatherState;
    }

    /**
     * Sets weather state.
     *
     * @param weatherState the weather state
     */
    public void setWeatherState(WeatherState weatherState) {
        this.weatherState = weatherState;
    }

    /**
     * Gets temperature.
     *
     * @return the temperature
     */
    public Temperature getTemperature() {
        return temperature;
    }

    /**
     * Sets temperature.
     *
     * @param temperature the temperature
     */
    public void setTemperature(Temperature temperature) {
        this.temperature = temperature;
    }

    /**
     * Gets atmospheric pressure.
     *
     * @return the atmospheric pressure
     */
    public AtmosphericPressure getAtmosphericPressure() {
        return atmosphericPressure;
    }

    /**
     * Sets atmospheric pressure.
     *
     * @param atmosphericPressure the atmospheric pressure
     */
    public void setAtmosphericPressure(AtmosphericPressure atmosphericPressure) {
        this.atmosphericPressure = atmosphericPressure;
    }

    /**
     * Gets humidity.
     *
     * @return the humidity
     */
    public Humidity getHumidity() {
        return humidity;
    }

    /**
     * Sets humidity.
     *
     * @param humidity the humidity
     */
    public void setHumidity(Humidity humidity) {
        this.humidity = humidity;
    }

    /**
     * Gets clouds.
     *
     * @return the clouds
     */
    public Clouds getClouds() {
        return clouds;
    }

    /**
     * Sets clouds.
     *
     * @param clouds the clouds
     */
    public void setClouds(Clouds clouds) {
        this.clouds = clouds;
    }

    /**
     * Gets visibility in metres.
     *
     * @return the visibility in metres
     */
    public Double getVisibilityInMetres() {
        return visibilityInMetres;
    }

    /**
     * Sets visibility in metres.
     *
     * @param visibilityInMetres the visibility in metres
     */
    public void setVisibilityInMetres(Double visibilityInMetres) {
        this.visibilityInMetres = visibilityInMetres;
    }

    /**
     * Gets wind.
     *
     * @return the wind
     */
    public Wind getWind() {
        return wind;
    }

    /**
     * Sets wind.
     *
     * @param wind the wind
     */
    public void setWind(Wind wind) {
        this.wind = wind;
    }

    /**
     * Gets rain.
     *
     * @return the rain
     */
    public Rain getRain() {
        return rain;
    }

    /**
     * Sets rain.
     *
     * @param rain the rain
     */
    public void setRain(Rain rain) {
        this.rain = rain;
    }

    /**
     * Gets snow.
     *
     * @return the snow
     */
    public Snow getSnow() {
        return snow;
    }

    /**
     * Sets snow.
     *
     * @param snow the snow
     */
    public void setSnow(Snow snow) {
        this.snow = snow;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        HourlyHistorical that = (HourlyHistorical) o;
        return Objects.equals(forecastTime, that.forecastTime) &&
                Objects.equals(weatherState, that.weatherState) &&
                Objects.equals(temperature, that.temperature) &&
                Objects.equals(atmosphericPressure, that.atmosphericPressure) &&
                Objects.equals(humidity, that.humidity) &&
                Objects.equals(clouds, that.clouds) &&
                Objects.equals(visibilityInMetres, that.visibilityInMetres) &&
                Objects.equals(wind, that.wind) &&
                Objects.equals(rain, that.rain) &&
                Objects.equals(snow, that.snow);
    }

    @Override
    public int hashCode() {
        return Objects.hash(forecastTime, weatherState, temperature, atmosphericPressure, humidity, clouds, visibilityInMetres, wind, rain, snow);
    }

    @Override
    public String toString() {
        return "Historical hourly information forecasted for " + forecastTime + ".";
    }
}
