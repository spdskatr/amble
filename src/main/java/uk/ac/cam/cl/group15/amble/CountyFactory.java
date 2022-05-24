package uk.ac.cam.cl.group15.amble;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

public class CountyFactory {

    private static final String baseURL = "http://www.gps-routes.co.uk";
    private static final String walkURL = "http://www.gps-routes.co.uk/routes/home.nsf/county-walks/";

    /**
     * @return a list of the county names on the county walks webpage
     */
    public static List<String> getCountyNames() {
        try {
            Document doc = Jsoup.connect(walkURL).get();
            // The second table contains names of counties.
            Element table = doc.getElementsByTag("tbody").get(1);
            // The first row does not contain the name of a county.
            table.child(0).remove();
            // iterate through the table (and appropriately traverse
            // the children of each row) to accumulate county names
            return table.children().stream().
                    map(row -> row.child(0).child(1).text()).
                    collect(Collectors.toList());
        } catch (IOException e) {
            throw new IllegalStateException(
                    "[getCountyNames]: The county walks webpage cannot be accessed.");
        } catch (IndexOutOfBoundsException e) {
            throw new IllegalStateException(
                    "[getCountyNames]: The county walks webpage is not of the expected format.");
        }
    }

    /**
     * Retrieves the URL of a route's GPX file from the route's page (at the provided URL).
     * Throws `IllegalArgumentException` iff the provided URL is unreachable or the webpage
     * at the provided URL is not of the expected format.
     * @param pageURL the URL of the route's page
     * @return the URL of the route's GPX file
     */
    private static String getDownloadURLFromPageURL(String pageURL) {
        try {
            Document doc = Jsoup.connect(pageURL).get();
            // The anchor element that contains the link to the route is always
            // the only sibling of a 'h2' element that is of the class 'gpsfiles'.
            Element header = doc.getElementsByClass("gpsfiles").get(0);
            Element anchor = header.siblingElements().tagName("a").get(0);

            // download the route from the (URL formatted) acquired link
            return baseURL + anchor.attr("href")
                    .replaceAll(" ", "%20");
        } catch (IOException e) {
            throw new IllegalArgumentException(
                    "[downloadRoute]: The provided URL is unreachable.");
        } catch (IndexOutOfBoundsException e) {
            throw new IllegalArgumentException(
                    "[downloadRoute]: The webpage at the provided URL is not of the expected format.");
        }
    }

    /**
     * Builds a route's `Route` object from its table entry (on a county's webpage).
     * Throws `IllegalArgumentException` if the provided row is not of the expected format.
     * @param row the table entry of the route
     * @return the route's `Route` object
     */
    private static Route getRouteFromRow(Element row) {

        try {
            // retrieve the name of the route and the URL to its GPX file
            Element anchor = row.child(0).child(0);
            String pageURL = anchor.attr("href");
            String url = getDownloadURLFromPageURL(pageURL);
            String name = anchor.text();
            // retrieve the distance of (number of miles in) the route
            Element distCell = row.child(1);
            String distString = distCell.text().split(" miles", 2)[0];
            int dist = Integer.parseInt(distString);
            return new Route(name, url, dist);
        } catch (IndexOutOfBoundsException e) {
            throw new IllegalArgumentException(
                    "[getRouteFromRow]: The provided row is not of the expected format.");
        }
    }

    public static County buildCounty(String county) {
        return buildCounty(county, null);
    }

    /**
     * Builds a list of a county's routes.
     * Throws `IllegalArgumentException` if the county's webpage is not accessible or not of the expected format.
     * @param county the county (as stated above)
     * @param count the number of routes to include.
     * @return a list of the county's routes
     */
    public static County buildCounty(String county, Integer count) {
        try {
            // obtain the county's webpage
            Document doc = Jsoup.connect(walkURL + county).get();
            // The second table always contains links to routes.
            Element table = doc.getElementsByTag("tbody").get(1);
            // The first row never contains a link to a route.
            table.child(0).remove();
            // retrieve the county's routes and build a `County` object
            Stream<Element> rowStream = table.children().stream();
            if (count != null) {
                rowStream = rowStream.limit(count);
            }
            List<Route> routes = rowStream.
                    map(row -> {
                        try {
                            return CountyFactory.getRouteFromRow(row);
                        } catch (IllegalArgumentException e) {
                            System.out.println(e.getMessage());
                            return null;
                        }}).
                    filter(Objects::nonNull).
                    sorted(Comparator.comparingInt(Route::getDistance)).
                    collect(Collectors.toList());
            return new County(routes);
        } catch (IndexOutOfBoundsException | IllegalArgumentException e) {
            throw new IllegalArgumentException(
                    "[getCountyRoutes]: The webpage at the provided URL is not of the expected format.");
        } catch (IOException e) {
            throw new IllegalArgumentException(
                    "[getCountyRoutes]: The provided county does not have an accessible webpage.");
        }
    }
}
