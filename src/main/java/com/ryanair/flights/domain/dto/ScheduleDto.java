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
public class ScheduleDto {
    private int month;
    private List<DayDto> days;

    public ScheduleDto() {
        days = new ArrayList<>();
    }

}
