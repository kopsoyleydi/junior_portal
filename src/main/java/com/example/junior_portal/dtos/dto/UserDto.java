package com.example.junior_portal.dtos.dto;


import jakarta.persistence.ManyToMany;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class UserDto {
    private Long id;

    private String username;

    private String email;

    private String password;

    @ManyToMany
    private List<PermissionDto> permissions;
}
