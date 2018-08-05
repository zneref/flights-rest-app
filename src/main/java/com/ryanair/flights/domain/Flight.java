package com.ryanair.flights.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Flight {
    private LocalDate flightDate;
    private LocalTime departureTime;
    private LocalTime arrivalTime;

    public LocalDateTime getDepartureDateTime() {
        return LocalDateTime.of(flightDate, departureTime);
    }

    public LocalDateTime getArrivalDateTime() {
        return LocalDateTime.of(flightDate, arrivalTime);
    }

}

