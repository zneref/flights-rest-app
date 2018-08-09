package com.ryanair.flights.service;

import com.ryanair.flights.domain.Connection;
import com.ryanair.flights.domain.dto.ConnectionDto;
import com.ryanair.flights.mapper.ConnectionMapper;
import com.ryanair.flights.service.retriever.IConnectionRetriever;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
@RequiredArgsConstructor
public class ConnectionService {
    private final IConnectionRetriever connectionRetriever;
    private final ConnectionMapper connectionMapper;

    public List<ConnectionDto> retrieveConnections(String departure, String arrival,
                                                   LocalDateTime departureDateTime, LocalDateTime arrivalDateTime) {
        List<Connection> direct = connectionRetriever.retrieveDirect(departure, arrival,
                departureDateTime, arrivalDateTime);
        List<Connection> interconnected = connectionRetriever.retrieveInterconnected(departure, arrival,
                departureDateTime, arrivalDateTime);

        List<Connection> connections = Stream.concat(direct.stream(), interconnected.stream())
                .collect(Collectors.toList());

        return connectionMapper.mapToConnectionsDto(connections);
    }
}
