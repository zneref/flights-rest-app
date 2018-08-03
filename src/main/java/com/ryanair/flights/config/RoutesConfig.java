package com.ryanair.flights.config;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Getter
@Component
public class RoutesConfig {
    @Value("${routes.api.endpoint}")
    private String routesApiEndpoint;
}
