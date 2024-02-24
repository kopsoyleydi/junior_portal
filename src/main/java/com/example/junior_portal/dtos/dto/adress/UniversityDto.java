package com.example.junior_portal.dtos.dto.adress;

import jakarta.persistence.OneToOne;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class UniversityDto {

    private Long id;
    private String name;

    private String webSite;

    @OneToOne
    private CityDto city;

    private String street;

    private String buildingNumber;
}
