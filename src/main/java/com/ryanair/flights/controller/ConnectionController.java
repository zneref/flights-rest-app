package com.ryanair.flights.controller;


import com.ryanair.flights.domain.dto.ConnectionDto;
import com.ryanair.flights.service.ConnectionService;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/v1")
@CrossOrigin("*")
@RequiredArgsConstructor
public class ConnectionController {
    private final ConnectionService connectionService;

    @GetMapping(value = "/interconnections")
    public List<ConnectionDto> getConnections(@RequestParam("departure") final String departure,
                                              @RequestParam("arrival") final String arrival,
                                              @RequestParam("departureDateTime")
                                              @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) final LocalDateTime departureDateTime,
                                              @RequestParam("arrivalDateTime")
                                              @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) final LocalDateTime arrivalDateTime) {

        return connectionService.retrieveConnections(departure, arrival, departureDateTime, arrivalDateTime);
    }
}
