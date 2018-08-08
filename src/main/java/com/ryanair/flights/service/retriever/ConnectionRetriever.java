package com.ryanair.flights.service.retriever;

import com.ryanair.flights.config.ConnectionConfig;
import com.ryanair.flights.domain.*;
import com.ryanair.flights.service.RouteService;
import com.ryanair.flights.service.ScheduleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ConnectionRetriever {
    private final ConnectionConfig connectionConfig;
    private final RouteService routeService;
    private final ScheduleService scheduleService;

    public List<Connection> retrieveDirect(String departure, String arrival,
                                           LocalDateTime departureDateTime, LocalDateTime arrivalDateTime) {
        List<Connection> connections = new ArrayList<>();
        List<Route> routes = routeService.fetchDirectRoutes(departure, arrival);

        for (Route route : routes) {
            Schedule schedule = scheduleService.fetchSchedule(route.getAirportFrom(),
                    route.getAirportTo(), departureDateTime.getYear(), departureDateTime.getMonthValue());
            List<Flight> flights = getFlights(departureDateTime, arrivalDateTime, schedule);
            List<ConnectionDetails> connectionDetailsList =
                    getConnectionDetailsList(route.getAirportFrom(), route.getAirportTo(), flights);
            connectionDetailsList.forEach(connectionDetails -> connections.add(new Connection(0,
                    Arrays.asList(connectionDetails))));
        }
        return connections;
    }

    public List<Connection> retrieveInterconnected(String departure, String arrival,
                                                   LocalDateTime departureDateTime, LocalDateTime arrivalDateTime) {
        List<Connection> connections = new ArrayList<>();
        List<Route> routes = routeService.fetchInterconnectedRoutes(departure, arrival);

        for (Route route : routes) {
            Schedule departureSchedule = scheduleService.fetchSchedule(route.getAirportFrom(),
                    route.getConnectingAirport(), departureDateTime.getYear(), departureDateTime.getMonthValue());
            Schedule arrivalSchedule = scheduleService.fetchSchedule(route.getConnectingAirport(),
                    route.getAirportTo(), departureDateTime.getYear(), departureDateTime.getMonthValue());

            List<Flight> departureFlights = getFlights(departureDateTime, arrivalDateTime, departureSchedule);
            List<Flight> arrivalFlights = getFlights(departureDateTime, arrivalDateTime, arrivalSchedule);

            List<ConnectionDetails> departureConnectionDetailsList = getConnectionDetailsList(route.getAirportFrom(),
                    route.getConnectingAirport(), departureFlights);
            List<ConnectionDetails> arrivalConnectionDetailsList = getConnectionDetailsList(route.getConnectingAirport(),
                    route.getAirportTo(), arrivalFlights);

            for (ConnectionDetails departureConnectionDetails : departureConnectionDetailsList) {
                Optional<ConnectionDetails> arrivalConnectionDetails = arrivalConnectionDetailsList.stream()
                        .filter(connection -> connection.getDepartureDateTime()
                                .isAfter(departureConnectionDetails.getArrivalDateTime().plusHours(connectionConfig.getHourOffset())))
                        .findFirst();

                if (arrivalConnectionDetails.isPresent()) {
                    List<ConnectionDetails> connectionDetailsList =
                            Arrays.asList(departureConnectionDetails, arrivalConnectionDetails.get());
                    connections.add(new Connection(1, connectionDetailsList));
                }
            }
        }
        return connections;
    }

    private List<Flight> getFlights(LocalDateTime departureDateTime, LocalDateTime arrivalDateTime, Schedule schedule) {
        return schedule.getDays().stream()
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

    private List<ConnectionDetails> getConnectionDetailsList(String departure, String arrival, List<Flight> flights) {
        return flights.stream()
                .map(flight -> new ConnectionDetails(departure, arrival, flight.getDepartureDateTime(), flight.getArrivalDateTime()))
                .collect(Collectors.toList());
    }
}
