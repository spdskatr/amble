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

package uk.ac.cam.cl.group15.amble.openweathermapapi.model.onecall;

import java.util.Objects;

/**
 * Represents rain information.
 */
public class Rain {
    private static final String DEFAULT_UNIT = "mm";

    private double oneHourLevel;

    private Rain() {
    }

    /**
     * Creates {@link Rain} object with correctness check.
     *
     * @param oneHourLevel 1-hour rain level value
     * @return rain object.
     */
    public static Rain withOneHourLevelValue(double oneHourLevel) {
        final Rain rain = new Rain();
        rain.setOneHourLevel(oneHourLevel);
        return rain;
    }

    /**
     * Gets one hour rain level.
     *
     * @return the one hour rain level
     */
    public double getOneHourLevel() {
        return oneHourLevel;
    }

    /**
     * Sets one hour rain level.
     *
     * @param oneHourLevel the one hour rain level
     */
    public void setOneHourLevel(double oneHourLevel) {
        if (oneHourLevel < 0) {
            throw new IllegalArgumentException("Rain level value cannot be negative.");
        }
        this.oneHourLevel = oneHourLevel;
    }

    /**
     * Gets unit.
     *
     * @return the unit
     */
    public String getUnit() {
        return DEFAULT_UNIT;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Rain)) return false;
        Rain rain = (Rain) o;
        return Objects.equals(oneHourLevel, rain.oneHourLevel);
    }

    @Override
    public int hashCode() {
        return Objects.hash(oneHourLevel);
    }

    @Override
    public String toString() {
        return "1-hour rain level: " +
                oneHourLevel + ' ' +
                getUnit();
    }
}
