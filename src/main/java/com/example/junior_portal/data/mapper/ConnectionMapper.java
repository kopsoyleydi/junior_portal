package com.example.junior_portal.data.mapper;

import com.example.junior_portal.dtos.dto.ConnectionDto;
import com.example.junior_portal.model.Connection;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ConnectionMapper {
    ConnectionDto toDto(Connection connection);

    Connection toModel(ConnectionDto connectionDto);

    List<ConnectionDto> toDtoList(List<Connection> list);

    List<Connection> toModelList(List<ConnectionDto> list);
}
