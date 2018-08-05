package com.ryanair.flights.validator;

import com.ryanair.flights.domain.Route;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@Component
public class RouteValidator {
    public List<Route> validateInterconnectedRoutes(List<Route> routes, String airportFrom, String airportTo) {
        List<Predicate<Route>> routesPredicates = new ArrayList<>();
        routesPredicates.add(route -> airportFrom.equalsIgnoreCase(route.getAirportFrom()));
        routesPredicates.add(route -> airportTo.equalsIgnoreCase(route.getAirportTo()));
        Predicate<Route> allFromOrTo = routesPredicates.stream().reduce(predicate -> false, Predicate::or);
 //       Predicate<Route> direct = routesPredicates.stream().reduce(predicate -> true, Predicate::and);

        List<Route> prefilteredRoutes = routes.stream()
                .filter(allFromOrTo).collect(Collectors.toList());

        List<Predicate<Route>> prefilteredRoutesPreds = new ArrayList<>();
 //       prefilteredRoutesPreds.add(direct);
        prefilteredRoutesPreds.add(route -> prefilteredRoutes.stream()
                .filter((anotherRoute -> route.getAirportTo().equalsIgnoreCase(anotherRoute.getAirportFrom())))
                .count() == 1);
        Predicate<Route> directAndInterconnected = prefilteredRoutesPreds.stream().reduce(predicate -> false, Predicate::or);

        return prefilteredRoutes.stream()
                .filter(directAndInterconnected)
                .map(route -> new Route(airportFrom, airportTo, route.getAirportTo()))
                .collect(Collectors.toList());
    }

    public List<Route> validateDirectRoutes(List<Route> routes, String airportFrom, String airportTo) {
        List<Predicate<Route>> routesPredicates = new ArrayList<>();
        routesPredicates.add(route -> airportFrom.equalsIgnoreCase(route.getAirportFrom()));
        routesPredicates.add(route -> airportTo.equalsIgnoreCase(route.getAirportTo()));
        Predicate<Route> onlyDirect = routesPredicates.stream().reduce(predicate -> true, Predicate::and);

        return routes.stream().filter(onlyDirect).collect(Collectors.toList());
    }
}
