package com.ryanair.flights.validator;

import org.springframework.stereotype.Component;

import java.util.stream.Stream;

@Component
public class IataParametersValidator {
    public boolean isIataParametersValid(String from, String to) {
        return Stream.of(from, to).allMatch(s -> s.matches("^[A-Z]{3}$"));
    }
}
