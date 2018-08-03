package com.ryanair.flights.mapper;

import com.ryanair.flights.domain.Route;
import com.ryanair.flights.domain.dto.RouteDto;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class RouteMapper {
    public List<Route> mapToRoutes(final List<RouteDto> routesDto) {
        return routesDto.stream()
                .map(routeDto -> new Route(routeDto.getAirportFrom(), routeDto.getAirportTo(), routeDto.getConnectingAirport()))
                .collect(Collectors.toList());
    }

    public List<RouteDto> mapToRoutesDto(final List<Route> routes) {
        return routes.stream()
                .map(route -> new RouteDto(route.getAirportFrom(), route.getAirportTo(), route.getOnnectingAirport()))
                .collect(Collectors.toList());
    }
}
