package com.ryanair.flights.service;

import com.ryanair.flights.apis.client.ScheduleClient;
import com.ryanair.flights.domain.Schedule;
import com.ryanair.flights.mapper.ScheduleMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ScheduleService {
    private final ScheduleClient scheduleClient;
    private final ScheduleMapper scheduleMapper;

    public Schedule fetchSchedule(String departure, String arrival, int year, int month) {
        return scheduleMapper.mapToSchedule(scheduleClient.getSchedules(departure, arrival, year, month));
    }
}
