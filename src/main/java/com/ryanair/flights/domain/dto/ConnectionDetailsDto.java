package com.ryanair.flights.domain.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class ConnectionDetailsDto {
    private String departureAirport;
    private String arrivalAirport;
    @JsonFormat(pattern = "YYYY-MM-dd HH:mm")
    private LocalDateTime departureDateTime;
    @JsonFormat(pattern = "YYYY-MM-dd HH:mm")
    private LocalDateTime arrivalDateTime;
}
