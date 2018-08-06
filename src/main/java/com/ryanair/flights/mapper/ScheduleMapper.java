package com.ryanair.flights.mapper;

import com.ryanair.flights.domain.Schedule;
import com.ryanair.flights.domain.dto.ScheduleDto;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ScheduleMapper {
    private final ModelMapper modelMapper;

    public ScheduleDto mapToScheduleDto(final Schedule schedule) {
        return modelMapper.map(schedule, ScheduleDto.class);
    }

    public Schedule mapToSchedule(final ScheduleDto scheduleDto) {
        return modelMapper.map(scheduleDto, Schedule.class);
    }
}
