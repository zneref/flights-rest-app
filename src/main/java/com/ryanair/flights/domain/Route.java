package com.ryanair.flights.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class Route {
    private String airportFrom;
    private String airportTo;
    private String onnectingAirport;
}
