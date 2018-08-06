package com.ryanair.flights.domain.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class DayDto {
    private int day;
    private List<FlightDto> flights;

    public DayDto() {
        flights = new ArrayList<>();
    }
}
