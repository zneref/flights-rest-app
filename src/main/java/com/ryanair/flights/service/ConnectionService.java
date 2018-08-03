package com.ryanair.flights.service;

import com.ryanair.flights.apis.client.RouteClient;
import com.ryanair.flights.apis.client.ScheduleClient;
import com.ryanair.flights.domain.dto.ConnectionDetailsDto;
import com.ryanair.flights.domain.dto.ConnectionDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class ConnectionService {
    @Autowired
    RouteClient routeClient;
    @Autowired
    ScheduleClient scheduleClient;

    public List<ConnectionDto> retrieveConnection(final String departure, final String arrival,
                                                  LocalDateTime departureDateTime, LocalDateTime arrivalDateTime) {
        ConnectionDetailsDto details = new ConnectionDetailsDto(departure, arrival, departureDateTime, arrivalDateTime);
        List<ConnectionDetailsDto> detailsList = new ArrayList<>();
        detailsList.add(details);
        ConnectionDto conn = new ConnectionDto(0, detailsList);
        List<ConnectionDto> l = new ArrayList<>();
        l.add(conn);
        return l;
    }
}
