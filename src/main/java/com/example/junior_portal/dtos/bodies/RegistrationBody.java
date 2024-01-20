package com.example.junior_portal.dtos.bodies;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RegistrationBody {
    private Long id;

    private String username;

    private String email;

    private String password;
}
