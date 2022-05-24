package uk.ac.cam.cl.group15.amble;

import com.sothawo.mapjfx.Coordinate;
import com.sothawo.mapjfx.CoordinateLine;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.parser.Parser;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.stream.Collectors;

public class Route {

    private final String name;
    private final String url;
    private final int distance;

    public Route(String name, String url, int distance) {
        this.name = name;
        this.url = url;
        this.distance = distance;
    }

    public String getName() {
        return name;
    }

    public String getURL() {
        return url;
    }

    public int getDistance() {
        return distance;
    }

    public int getTime() { return distance * 12; }

    public Integer getDistanceBound(List<Integer> distances) {
        return distances.stream().filter(bound -> bound > distance)
                .findFirst().orElse(null);
    }

    /** Builds a `CoordinateLine` object for this route
     * Throws `IOException` iff this route's URL is unreachable.
     * @return a `CoordinateLine` object for this route
     */
    public CoordinateLine getCoordinateLine() {
        try {
            Document doc = Jsoup.parse(new URL(url).openStream(), "UTF-8", "", Parser.xmlParser());
            // A GPX file stores the latitude and longitude of points in `rtept` elements.
            Elements points = doc.getElementsByTag("rtept");
            List<Coordinate> coords = points.stream().
                    map(point -> new Coordinate(
                            Double.parseDouble(point.attr("lat")),
                            Double.parseDouble(point.attr("lon")))).
                    collect(Collectors.toList());
            return new CoordinateLine(coords);
        } catch (IOException e) {
            throw new IllegalArgumentException("[getCoordinateLine]: The route's URL is unreachable.");
        }
    }
}
