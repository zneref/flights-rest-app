package com.ryanair.flights.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class ConnectionDto {
    private int stops;
    private List<ConnectionDetailsDto> legs;

}
