package com.ryanair.flights.validator;

import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
public class IataParametersValidator {
    public boolean isIataParametersValid(String from, String to) {
        List<String> params = Arrays.asList(from, to);

        return params.stream().filter(s -> s.matches("^[A-Z]{3}$")).count() == 2;
    }
}
