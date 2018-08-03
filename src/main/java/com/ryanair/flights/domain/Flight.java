package com.ryanair.flights.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalTime;

@Getter
@AllArgsConstructor
public class Flight {
    private int number;
    private LocalTime departureTime;
    private LocalTime arrivalTime;
}

