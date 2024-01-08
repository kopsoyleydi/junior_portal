package com.example.junior_portal.data.mapper;

import com.example.junior_portal.dtos.dto.ConnectionDto;
import com.example.junior_portal.model.Connection;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class ConnectionMapper {

    private final UserMapper userMapper;
    public ConnectionDto toDto(Connection connection){
        ConnectionDto connectionDto = new ConnectionDto();
        connectionDto.setId(connection.getId());
        connectionDto.setConnectionType(connection.getConnectionType());
        connectionDto.setUser1Id(userMapper.toDto(connection.getUser1Id()));
        connectionDto.setUser2Id(userMapper.toDto(connection.getUser2Id()));
        return connectionDto;
    };

    public Connection toModel(ConnectionDto connectionDto){
        Connection connection = new Connection();
        connection.setId(connectionDto.getId());
        connection.setConnectionType(connectionDto.getConnectionType());
        connection.setUser1Id(userMapper.toModel(connectionDto.getUser1Id()));
        connection.setUser2Id(userMapper.toModel(connectionDto.getUser2Id()));
        return connection;
    };

    public List<ConnectionDto> toDtoList(List<Connection> connections) {
        return connections.stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    public List<Connection> toModelList(List<ConnectionDto> connectionDtos) {
        return connectionDtos.stream()
                .map(this::toModel)
                .collect(Collectors.toList());
    }
}
