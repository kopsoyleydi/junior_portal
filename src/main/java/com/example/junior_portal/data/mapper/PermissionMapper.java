package com.example.junior_portal.data.mapper;

import com.example.junior_portal.dtos.dto.PermissionDto;
import com.example.junior_portal.model.Permission;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PermissionMapper {
    PermissionDto toDto(Permission permission);

    Permission toModel(PermissionDto permissionDto);

    List<PermissionDto> toDtoList(List<Permission> list);

    List<Permission> toModelList(List<PermissionDto> list);
}
