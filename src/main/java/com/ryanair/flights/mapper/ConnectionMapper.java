package com.ryanair.flights.mapper;

import com.ryanair.flights.domain.Connection;
import com.ryanair.flights.domain.dto.ConnectionDto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ConnectionMapper {
    @Autowired
    ModelMapper modelMapper;

    public List<ConnectionDto> mapToConnectionsDto(final List<Connection> connections) {
        return connections.stream()
                .map(this::convertToConnectionDto)
                .collect(Collectors.toList());
    }

    private ConnectionDto convertToConnectionDto(Connection connection) {
        return modelMapper.map(connection, ConnectionDto.class);
    }
}
