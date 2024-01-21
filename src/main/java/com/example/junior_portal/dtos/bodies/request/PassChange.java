package com.example.junior_portal.dtos.bodies.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PassChange {

    private String userEmail;

    private String password;

    private String repeatPassword;


}
