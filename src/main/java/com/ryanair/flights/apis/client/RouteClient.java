package com.ryanair.flights.apis.client;

import com.ryanair.flights.config.RoutesConfig;
import com.ryanair.flights.domain.dto.RouteDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static java.util.Optional.ofNullable;

@Component
public class RouteClient {
    private static final Logger LOGGER = LoggerFactory.getLogger(RouteClient.class);

    @Autowired
    RoutesConfig routesConfig;
    @Autowired
    RestTemplate restTemplate;

    public List<RouteDto> getRoutes() {
        URI url = UriComponentsBuilder.fromHttpUrl(routesConfig.getRoutesApiEndpoint()).build().encode().toUri();
        try {
            RouteDto[] routesResponse = restTemplate.getForObject(url, RouteDto[].class);
            return Arrays.asList(ofNullable(routesResponse).orElse(new RouteDto[0]));
        } catch (RestClientException e) {
            LOGGER.error(e.getMessage(), e);
            return new ArrayList<>();
        }
    }
}
