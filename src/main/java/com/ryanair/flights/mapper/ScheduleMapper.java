package com.ryanair.flights.mapper;

import com.ryanair.flights.domain.Schedule;
import com.ryanair.flights.domain.dto.ScheduleDto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ScheduleMapper {
    @Autowired
    ModelMapper modelMapper;

    public ScheduleDto mapToScheduleDto(final Schedule schedule) {
        return modelMapper.map(schedule, ScheduleDto.class);
    }

    public Schedule mapToSchedule(final ScheduleDto scheduleDto) {
        return modelMapper.map(scheduleDto, Schedule.class);
    }
}
