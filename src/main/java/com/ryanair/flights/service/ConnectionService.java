package com.ryanair.flights.service;

import com.ryanair.flights.domain.dto.ConnectionDetailsDto;
import com.ryanair.flights.domain.dto.ConnectionDto;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class ConnectionService {
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
