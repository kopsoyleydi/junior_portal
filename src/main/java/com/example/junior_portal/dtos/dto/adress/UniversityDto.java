package com.example.junior_portal.dtos.dto.adress;

import jakarta.persistence.OneToOne;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class UniversityDto {

    String alpha_two_code;

    String state_province;

    List<String> domains;

    String name;

    List<String> webPages;

    String country;
}
