package com.ryanair.flights.config;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Getter
@Component
public class SchedulesConfig {
    @Value("${schedules.api.endpoint}")
    private String schedulesApiEndpoint;
}
