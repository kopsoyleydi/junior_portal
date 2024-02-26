package com.example.junior_portal.dtos.dto;


import jakarta.persistence.ManyToMany;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class UserDto {
    private Long id;

    private String login;

    private String email;

    private String password;

    private String pictureLink;
    @ManyToMany
    private List<PermissionDto> permissions;
}
