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

package uk.ac.cam.cl.group15.amble.openweathermapapi.request.weather.single;

import uk.ac.cam.cl.group15.amble.openweathermapapi.model.Coordinate;
import uk.ac.cam.cl.group15.amble.openweathermapapi.request.RequestSettings;

/**
 * The type Single location current weather requester.
 */
public class SingleLocationCurrentWeatherRequester {
    private final RequestSettings requestSettings;

    /**
     * Instantiates a new Single location current weather requester.
     *
     * @param requestSettings request settings object.
     */
    public SingleLocationCurrentWeatherRequester(RequestSettings requestSettings) {
        this.requestSettings = requestSettings;
        this.requestSettings.appendToURL("weather");
    }

    public SingleResultCurrentWeatherRequestCustomizer byCityName(String cityName) {
        requestSettings.putRequestParameter("q", cityName);
        return new SingleResultCurrentWeatherRequestCustomizer(requestSettings);
    }

    public SingleResultCurrentWeatherRequestCustomizer byCityName(String cityName, String countryCode) {
        requestSettings.putRequestParameter("q", cityName + "," + countryCode);
        return new SingleResultCurrentWeatherRequestCustomizer(requestSettings);
    }

    public SingleResultCurrentWeatherRequestCustomizer byCityName(String cityName, String stateCode, String countryCode) {
        requestSettings.putRequestParameter("q", cityName + "," + stateCode + "," + countryCode);
        return new SingleResultCurrentWeatherRequestCustomizer(requestSettings);
    }

    public SingleResultCurrentWeatherRequestCustomizer byCityId(long cityId) {
        requestSettings.putRequestParameter("id", String.valueOf(cityId));
        return new SingleResultCurrentWeatherRequestCustomizer(requestSettings);
    }

    public SingleResultCurrentWeatherRequestCustomizer byCoordinate(Coordinate coordinate) {
        requestSettings.putRequestParameter("lat", String.valueOf(coordinate.getLatitude()));
        requestSettings.putRequestParameter("lon", String.valueOf(coordinate.getLongitude()));
        return new SingleResultCurrentWeatherRequestCustomizer(requestSettings);
    }

    public SingleResultCurrentWeatherRequestCustomizer byZipCodeAndCountry(String zipCode, String countryCode) {
        requestSettings.putRequestParameter("zip", zipCode + "," + countryCode);
        return new SingleResultCurrentWeatherRequestCustomizer(requestSettings);
    }

    public SingleResultCurrentWeatherRequestCustomizer byZipCodeInUSA(String zipCode) {
        requestSettings.putRequestParameter("zip", zipCode);
        return new SingleResultCurrentWeatherRequestCustomizer(requestSettings);
    }
}
