package com.ryanair.flights.service;

import com.ryanair.flights.apis.client.RouteClient;
import com.ryanair.flights.domain.dto.RouteDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RouteService {
    @Autowired
    RouteClient routeClient;

    public List<RouteDto> fetchRoutes() {
        return routeClient.getRoutes();
    }
}
