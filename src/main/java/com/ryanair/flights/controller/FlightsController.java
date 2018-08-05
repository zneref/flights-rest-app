package com.ryanair.flights.controller;


import com.ryanair.flights.domain.Route;
import com.ryanair.flights.domain.Schedule;
import com.ryanair.flights.domain.dto.ConnectionDto;
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
    public List<Route> getRoutes(@RequestParam("from") final String from,
                                 @RequestParam("to") final String to) {
        return routeService.fetchInterconnectedRoutes(from, to);
    }

    @GetMapping(value = "/schedules")
    public Schedule getSchedules() {

        return scheduleService.fetchSchedule("DUB", "WRO", 2018, 8);
    }

    @GetMapping(value = "/interconnections")
    public List<ConnectionDto> getFlights(@RequestParam("departure") final String departure,
                                          @RequestParam("arrival") final String arrival,
                                          @RequestParam("departureDateTime")
                                          @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) final LocalDateTime departureDateTime,
                                          @RequestParam("arrivalDateTime")
                                          @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) final LocalDateTime arrivalDateTime) {

        return connectionService.retrieveConnections(departure, arrival, departureDateTime, arrivalDateTime);
    }
}
