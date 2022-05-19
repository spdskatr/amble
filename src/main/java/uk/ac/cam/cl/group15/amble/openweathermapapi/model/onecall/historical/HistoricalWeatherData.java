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

import uk.ac.cam.cl.group15.amble.openweathermapapi.model.Coordinate;

import java.time.ZoneId;
import java.time.ZoneOffset;
import java.util.List;
import java.util.Objects;

/**
 * The type Historical weather data.
 */
public class HistoricalWeatherData {
    private Coordinate coordinate;
    private ZoneId timezone;
    private ZoneOffset timezoneOffset;

    private HistoricalWeather historicalWeather;
    private List<HourlyHistorical> hourlyList;

    /**
     * Gets coordinate.
     *
     * @return the coordinate
     */
    public Coordinate getCoordinate() {
        return coordinate;
    }

    /**
     * Sets coordinate.
     *
     * @param coordinate the coordinate
     */
    public void setCoordinate(Coordinate coordinate) {
        this.coordinate = coordinate;
    }

    /**
     * Gets timezone.
     *
     * @return the timezone
     */
    public ZoneId getTimezone() {
        return timezone;
    }

    /**
     * Sets timezone.
     *
     * @param timezone the timezone
     */
    public void setTimezone(ZoneId timezone) {
        this.timezone = timezone;
    }

    /**
     * Gets timezone offset.
     *
     * @return the timezone offset
     */
    public ZoneOffset getTimezoneOffset() {
        return timezoneOffset;
    }

    /**
     * Sets timezone offset.
     *
     * @param timezoneOffset the timezone offset
     */
    public void setTimezoneOffset(ZoneOffset timezoneOffset) {
        this.timezoneOffset = timezoneOffset;
    }

    /**
     * Gets historical weather.
     *
     * @return the historical weather
     */
    public HistoricalWeather getHistoricalWeather() {
        return historicalWeather;
    }

    /**
     * Sets historical weather.
     *
     * @param historicalWeather the historical weather
     */
    public void setHistoricalWeather(HistoricalWeather historicalWeather) {
        this.historicalWeather = historicalWeather;
    }

    /**
     * Gets hourly list.
     *
     * @return the hourly list
     */
    public List<HourlyHistorical> getHourlyList() {
        return hourlyList;
    }

    /**
     * Sets hourly list.
     *
     * @param hourlyList the hourly list
     */
    public void setHourlyList(List<HourlyHistorical> hourlyList) {
        this.hourlyList = hourlyList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        HistoricalWeatherData that = (HistoricalWeatherData) o;
        return Objects.equals(coordinate, that.coordinate) &&
                Objects.equals(timezone, that.timezone) &&
                Objects.equals(timezoneOffset, that.timezoneOffset) &&
                Objects.equals(historicalWeather, that.historicalWeather) &&
                Objects.equals(hourlyList, that.hourlyList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(coordinate, timezone, timezoneOffset, historicalWeather, hourlyList);
    }

    @Override
    public String toString() {
        return "Historical weather data for " + coordinate + ".";
    }
}
