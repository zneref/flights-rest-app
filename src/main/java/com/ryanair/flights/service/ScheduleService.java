package com.ryanair.flights.service;

import com.ryanair.flights.apis.client.ScheduleClient;
import com.ryanair.flights.domain.Schedule;
import com.ryanair.flights.mapper.ScheduleMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ScheduleService {
    @Autowired
    ScheduleClient scheduleClient;
    @Autowired
    ScheduleMapper scheduleMapper;

    public Schedule fetchSchedule(String departure, String arrival, int year, int month) {
        return scheduleMapper.mapToSchedule(scheduleClient.getSchedules(departure, arrival, year, month));
    }
}
