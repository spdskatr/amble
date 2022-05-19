/*
 *
 *  * Copyright (c) 2021 Alexey Zinchenko
 *  *
 *  * Permission is hereby granted, free of charge, to any person obtaining a copy
 *  * of this software and associated documentation files (the "Software"), to deal
 *  * in the Software without restriction, including without limitation the rights
 *  * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 *  * copies of the Software, and to permit persons to whom the Software is
 *  * furnished to do so, subject to the following conditions:
 *  *
 *  * The above copyright notice and this permission notice shall be included in all
 *  * copies or substantial portions of the Software.
 *  *
 *  * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 *  * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 *  * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 *  * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 *  * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 *  * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 *  * SOFTWARE.
 *
 */

package uk.ac.cam.cl.group15.amble.openweathermapapi.request.air.pollution;

import uk.ac.cam.cl.group15.amble.openweathermapapi.mapper.AirPollutionResponseMapper;
import uk.ac.cam.cl.group15.amble.openweathermapapi.model.air.pollution.AirPollutionDetails;
import uk.ac.cam.cl.group15.amble.openweathermapapi.request.RequestSettings;
import uk.ac.cam.cl.group15.amble.openweathermapapi.utils.RequestUtils;

import java.util.concurrent.CompletableFuture;

/**
 * The type Air pollution async request terminator.
 */
public class AirPollutionAsyncRequestTerminator {
    private final RequestSettings requestSettings;

    /**
     * Instantiates a new Air pollution async request terminator.
     *
     * @param requestSettings request settings object.
     */
    public AirPollutionAsyncRequestTerminator(RequestSettings requestSettings) {
        this.requestSettings = requestSettings;
    }

    public CompletableFuture<AirPollutionDetails> asJava() {
        return CompletableFuture.supplyAsync(() -> new AirPollutionResponseMapper().mapToAirPollution(getRawResponse()));
    }

    public CompletableFuture<String> asJSON() {
        return CompletableFuture.supplyAsync(this::getRawResponse);
    }

    private String getRawResponse() {
        return RequestUtils.getResponse(requestSettings);
    }
}
