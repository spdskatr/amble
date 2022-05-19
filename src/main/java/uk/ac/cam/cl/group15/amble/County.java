package uk.ac.cam.cl.group15.amble;

import java.util.ArrayList;
import java.util.List;

public class County {

    private final List<Route> routes;

    public County(List<Route> routes) {
        this.routes = routes;
    }

    public List<Route> getBoundedRoutes(int lowerBound, int upperBound) {
        List<Route> boundedRoutes = new ArrayList<>();
        for (Route route : routes) {
            if (lowerBound <= route.getDistance() && route.getDistance() <= upperBound) {
                boundedRoutes.add(route);}
        }
        return boundedRoutes;
    }
}
