package com.example.junior_portal.dtos.dto;


import jakarta.persistence.OneToOne;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProfileDto {

    private Long id;

    @OneToOne
    private UserDto userId;

    private String name;

    private String bio;

    private String experience;

    private String university;
}
