package com.ryanair.flights.controller;


import com.ryanair.flights.domain.dto.ConnectionDto;
import com.ryanair.flights.domain.dto.RouteDto;
import com.ryanair.flights.domain.dto.ScheduleDto;
import com.ryanair.flights.service.ConnectionService;
import com.ryanair.flights.service.RouteService;
import com.ryanair.flights.service.ScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/v1/flights")
@CrossOrigin("*")
public class FlightsController {
    @Autowired
    RouteService routeService;
    @Autowired
    ScheduleService scheduleService;
    @Autowired
    ConnectionService connectionService;

    @GetMapping(value = "/routes")
    public List<RouteDto> getRoutes() {
        return routeService.fetchRoutes();
    }

    @GetMapping(value = "/schedules")
    public ScheduleDto getSchedules() {
        return scheduleService.fetchSchedules("DUB", "WRO", 2018, 8);
    }

    @GetMapping(value = "/interconnections")
    public List<ConnectionDto> getFlights(@RequestParam("departure") final String departure,
                                          @RequestParam("arrival") final String arrival,
                                          @RequestParam("departureDateTime")
                                          @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) final LocalDateTime departureDateTime,
                                          @RequestParam("arrivalDateTime")
                                          @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) final LocalDateTime arrivalDateTime) {
        return connectionService.retrieveConnection(departure, arrival, departureDateTime, arrivalDateTime);
    }
}
