package com.ryanair.flights.service;

import com.ryanair.flights.apis.client.ScheduleClient;
import com.ryanair.flights.domain.dto.ScheduleDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ScheduleService {
    @Autowired
    ScheduleClient scheduleClient;

    public ScheduleDto fetchSchedules(String departure, String arrival, int year, int month) {
        return scheduleClient.getSchedules(departure, arrival, year, month);
    }
}
