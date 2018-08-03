package com.ryanair.flights.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class Schedule {
    private int month;
    private List<Day> days;
}
