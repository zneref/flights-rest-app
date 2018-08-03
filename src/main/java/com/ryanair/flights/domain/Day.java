package com.ryanair.flights.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class Day {
    private int day;
    private List<Flight> flights;
}
