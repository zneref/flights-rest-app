package com.ryanair.flights.service.retriever;

import com.ryanair.flights.domain.Connection;

import java.time.LocalDateTime;
import java.util.List;

public interface IConnectionRetriever {
    List<Connection> retrieveDirect(String departure, String arrival,
                                    LocalDateTime departureDateTime, LocalDateTime arrivalDateTime);

    List<Connection> retrieveInterconnected(String departure, String arrival,
                                            LocalDateTime departureDateTime, LocalDateTime arrivalDateTime);


}

