package com.ryanair.flights.config;

import lombok.Getter;
import org.springframework.stereotype.Component;

@Component
@Getter
public class ConnectionConfig {
    private int hourOffset = 2;
}
