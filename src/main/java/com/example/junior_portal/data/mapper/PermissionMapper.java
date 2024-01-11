package com.example.junior_portal.data.mapper;

import com.example.junior_portal.dtos.dto.PermissionDto;
import com.example.junior_portal.model.Permission;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class PermissionMapper {
    public PermissionDto toDto(Permission permission){
        PermissionDto permissionDto = new PermissionDto();
        permissionDto.setId(permission.getId());
        permissionDto.setRoleName(permission.getRoleName());
        return permissionDto;
    };

    public Permission toModel(PermissionDto permissionDto){
        Permission permission = new Permission();
        permission.setId(permission.getId());
        permission.setRoleName(permissionDto.getRoleName());
        return permission;
    };

    public List<PermissionDto> toDtoList(List<Permission> permissionList) {
        return permissionList.stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    public List<Permission> toModelList(List<PermissionDto> permissionDtoList) {
        return permissionDtoList.stream()
                .map(this::toModel)
                .collect(Collectors.toList());
    }
}
