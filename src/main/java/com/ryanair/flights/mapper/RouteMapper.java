package com.ryanair.flights.mapper;

import com.ryanair.flights.domain.Route;
import com.ryanair.flights.domain.dto.RouteDto;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class RouteMapper {
    private final ModelMapper modelMapper;

    public List<Route> mapToRoutes(final List<RouteDto> routesDto) {
        return routesDto.stream()
                .map(this::convertToRoute)
                .collect(Collectors.toList());
    }

    public List<RouteDto> mapToRoutesDto(final List<Route> routes) {
        return routes.stream()
                .map(this::convertToRouteDto)
                .collect(Collectors.toList());
    }

    private RouteDto convertToRouteDto(Route route) {
        return modelMapper.map(route, RouteDto.class);
    }

    private Route convertToRoute(RouteDto routeDto) {
        Route route = modelMapper.map(routeDto, Route.class);
        route.setConnectingAirport("EMPTY");
        return route;
    }
}
