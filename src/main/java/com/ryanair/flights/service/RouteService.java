package com.ryanair.flights.service;

import com.ryanair.flights.apis.client.RouteClient;
import com.ryanair.flights.domain.Route;
import com.ryanair.flights.domain.dto.RouteDto;
import com.ryanair.flights.mapper.RouteMapper;
import com.ryanair.flights.validator.RouteValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RouteService {
    @Autowired
    RouteClient routeClient;
    @Autowired
    RouteMapper routeMapper;
    @Autowired
    RouteValidator routeValidator;

    public List<RouteDto> fetchRoutes(String from, String to) {
        List<Route> routes = routeMapper.mapToRoutes(routeClient.getRoutes());
        return routeMapper.mapToRoutesDto(routeValidator.validateRoute(routes, from, to));
    }
}
