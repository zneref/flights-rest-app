package com.ryanair.flights.apis.client;

import com.ryanair.flights.config.SchedulesConfig;
import com.ryanair.flights.domain.dto.ScheduleDto;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.HashMap;
import java.util.Map;

import static java.util.Optional.ofNullable;

@Component
@RequiredArgsConstructor
public class ScheduleClient {
    private static final Logger LOGGER = LoggerFactory.getLogger(ScheduleClient.class);

    private final SchedulesConfig schedulesConfig;
    private final RestTemplate restTemplate;

    public ScheduleDto getSchedules(String departure, String arrival, int year, int month) {
        URI url = getSchedulesUrl(departure, arrival, year, month);

        try {
            ScheduleDto schedules = restTemplate.getForObject(url, ScheduleDto.class);
            return ofNullable(schedules).orElse(new ScheduleDto());

        } catch (RestClientException e) {
            LOGGER.error(e.getMessage(), e);
            return new ScheduleDto();
        }
    }

    private URI getSchedulesUrl(String departure, String arrival, int year, int month) {
        Map<String, String> uriParams = new HashMap<>();
        uriParams.put("departure", departure);
        uriParams.put("arrival", arrival);
        uriParams.put("year", Integer.toString(year));
        uriParams.put("month", Integer.toString(month));

        return UriComponentsBuilder.fromHttpUrl(schedulesConfig.getSchedulesApiEndpoint()
                + "/{departure}/{arrival}/years/{year}/months/{month}")
                .buildAndExpand(uriParams).toUri();
    }
}
