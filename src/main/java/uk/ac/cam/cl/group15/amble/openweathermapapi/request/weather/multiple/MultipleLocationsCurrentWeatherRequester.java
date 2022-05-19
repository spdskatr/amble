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

package uk.ac.cam.cl.group15.amble.openweathermapapi.request.weather.multiple;

import uk.ac.cam.cl.group15.amble.openweathermapapi.model.Coordinate;
import uk.ac.cam.cl.group15.amble.openweathermapapi.model.CoordinateRectangle;
import uk.ac.cam.cl.group15.amble.openweathermapapi.request.RequestSettings;

/**
 * The type Multiple locations current weather requester.
 */
public class MultipleLocationsCurrentWeatherRequester {
    private final RequestSettings requestSettings;

    /**
     * Instantiates a new Multiple locations current weather requester.
     *
     * @param requestSettings request settings object.
     */
    public MultipleLocationsCurrentWeatherRequester(RequestSettings requestSettings) {
        this.requestSettings = requestSettings;
    }

    public MultipleResultCurrentWeatherRequestCustomizer byRectangle(CoordinateRectangle rectangle, int zoom) {
        String coordinates = rectangle.getFormattedRequestString() + "," + zoom;
        requestSettings.appendToURL("box/city");
        requestSettings.putRequestParameter("bbox", coordinates);

        return new MultipleResultCurrentWeatherRequestCustomizer(requestSettings);
    }

    public MultipleResultCitiesInCircleCurrentWeatherRequestCustomizer byCitiesInCycle(Coordinate point, int citiesCount) {
        requestSettings.appendToURL("find");
        requestSettings.putRequestParameter("lat", Double.toString(point.getLatitude()));
        requestSettings.putRequestParameter("lon", Double.toString(point.getLongitude()));
        requestSettings.putRequestParameter("cnt", Integer.toString(citiesCount));

        return new MultipleResultCitiesInCircleCurrentWeatherRequestCustomizer(requestSettings);
    }

    public MultipleResultCitiesInCircleCurrentWeatherRequestCustomizer byCitiesInCycle(Coordinate point) {
        requestSettings.appendToURL("find");
        requestSettings.putRequestParameter("lat", Double.toString(point.getLatitude()));
        requestSettings.putRequestParameter("lon", Double.toString(point.getLongitude()));

        return new MultipleResultCitiesInCircleCurrentWeatherRequestCustomizer(requestSettings);
    }
}
