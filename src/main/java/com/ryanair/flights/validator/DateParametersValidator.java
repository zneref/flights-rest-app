package com.ryanair.flights.validator;

import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class DateParametersValidator {
    public boolean isDateParametersValid(LocalDateTime from, LocalDateTime to) {
        return from.isBefore(to);
    }
}
