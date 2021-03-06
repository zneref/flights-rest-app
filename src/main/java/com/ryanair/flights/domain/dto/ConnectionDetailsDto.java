package com.ryanair.flights.domain.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ConnectionDetailsDto {
    private String departureAirport;
    private String arrivalAirport;
    @JsonFormat(pattern = "YYYY-MM-dd'T'HH:mm")
    private LocalDateTime departureDateTime;
    @JsonFormat(pattern = "YYYY-MM-dd'T'HH:mm")
    private LocalDateTime arrivalDateTime;
}
