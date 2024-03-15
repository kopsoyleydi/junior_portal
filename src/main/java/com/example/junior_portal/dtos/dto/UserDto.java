package com.example.junior_portal.dtos.dto;


import jakarta.persistence.ManyToMany;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {
    private Long id;

    private String login;

    private String email;

    private String password;

    private String pictureLink;
    @ManyToMany
    private List<PermissionDto> permissions;
}
