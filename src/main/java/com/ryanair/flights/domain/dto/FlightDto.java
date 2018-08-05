package com.ryanair.flights.domain.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class FlightDto {
    @JsonFormat(pattern = "HH:mm")
    private LocalTime departureTime;
    @JsonFormat(pattern = "HH:mm")
    private LocalTime arrivalTime;
}
