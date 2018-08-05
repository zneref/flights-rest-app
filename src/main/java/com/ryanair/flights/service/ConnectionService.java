package com.ryanair.flights.service;

import com.ryanair.flights.domain.*;
import com.ryanair.flights.domain.dto.ConnectionDto;
import com.ryanair.flights.mapper.ConnectionMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class ConnectionService {
    private static final int HOUR_OFFSET = 2;
    @Autowired
    RouteService routeService;
    @Autowired
    ScheduleService scheduleService;
    @Autowired
    ConnectionMapper connectionMapper;

    public List<ConnectionDto> retrieveConnections(String departure, String arrival,
                                                   LocalDateTime departureDateTime, LocalDateTime arrivalDateTime) {
        List<Connection> connections = new ArrayList<>();

        List<Route> directRoutes = routeService.fetchDirectRoutes(departure, arrival);
        for (Route route : directRoutes) {
            Schedule schedule = scheduleService.fetchSchedule(route.getAirportFrom(),
                    route.getAirportTo(), departureDateTime.getYear(), departureDateTime.getMonthValue());
            List<Flight> flights = getFlights(departureDateTime, arrivalDateTime, schedule);
            List<ConnectionDetails> connectionDetailsList =
                    getConnectionDetailsList(route.getAirportFrom(), route.getAirportTo(), flights);
            if (!connectionDetailsList.isEmpty())
                connections.add(new Connection(0, connectionDetailsList));
        }
        List<Route> routes = routeService.fetchInterconnectedRoutes(departure, arrival);
        for (Route route : routes) {
            Schedule departureSchedule = scheduleService.fetchSchedule(route.getAirportFrom(),
                    route.getConnectingAirport(), departureDateTime.getYear(), departureDateTime.getMonthValue());
            Schedule arrivalSchedule = scheduleService.fetchSchedule(route.getConnectingAirport(),
                    route.getAirportTo(), departureDateTime.getYear(), departureDateTime.getMonthValue());

            List<Flight> departureFlights = getFlights(departureDateTime, arrivalDateTime, departureSchedule);
            List<Flight> arrivalFlights = getFlights(departureDateTime, arrivalDateTime, arrivalSchedule);

            List<ConnectionDetails> departureConnectionDetailsList =
                    getConnectionDetailsList(route.getAirportFrom(), route.getConnectingAirport(), departureFlights);
            List<ConnectionDetails> arrivalConnectionDetailsList =
                    getConnectionDetailsList(route.getConnectingAirport(), route.getAirportTo(), arrivalFlights);

            for (ConnectionDetails departureConnectionDetails : departureConnectionDetailsList) {
                Optional<ConnectionDetails> arrivalConnectionDetails = arrivalConnectionDetailsList.stream()
                        .filter(connection -> connection.getDepartureDateTime()
                                .isAfter(departureConnectionDetails.getArrivalDateTime().plusHours(HOUR_OFFSET)))
                        .findFirst();

                if (arrivalConnectionDetails.isPresent()) {
                    List<ConnectionDetails> connectionDetailsList = Arrays.asList(departureConnectionDetails, arrivalConnectionDetails.get());
                    connections.add(new Connection(1, connectionDetailsList));
                }
            }
        }

        return connectionMapper.mapToConnectionsDto(connections);
    }

    private List<Flight> getFlights(LocalDateTime departureDateTime, LocalDateTime arrivalDateTime, Schedule schedule) {
        List<Flight> flights = new ArrayList<>();
        List<Day> days = schedule.getDays();
        if (days != null) {
            flights = days.stream()
                    .flatMap(day -> day.getFlights().stream()
                            .map(flight -> {
                                        LocalDate flightDate = LocalDate.of(departureDateTime.getYear(), schedule.getMonth(), day.getDay());
                                        return new Flight(flightDate, flight.getDepartureTime(), flight.getArrivalTime());
                                    }
                            ))
                    .filter(flight -> flight.getDepartureDateTime().isAfter(departureDateTime)
                            && flight.getArrivalDateTime().isBefore(arrivalDateTime))
                    .sorted(Comparator.comparing(Flight::getDepartureDateTime))
                    .collect(Collectors.toList());
        }
        return flights;
    }

    private List<ConnectionDetails> getConnectionDetailsList(String departure, String arrival, List<Flight> flights) {
        return flights.stream()
                .map(flight -> connectionDetails(departure, arrival, flight))
                .collect(Collectors.toList());
    }

    private ConnectionDetails connectionDetails(String departure, String arrival, Flight flight) {
        LocalDateTime departureDateTime = flight.getDepartureDateTime();
        LocalDateTime arrivalDateTime = flight.getArrivalDateTime();
        return new ConnectionDetails(departure, arrival, departureDateTime, arrivalDateTime);
    }
}
