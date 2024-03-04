package com.example.junior_portal.dtos.dto;

import com.example.junior_portal.dtos.dto.adress.CityDto;
import jakarta.persistence.OneToOne;
import lombok.Data;
import org.springframework.stereotype.Component;

@Component
@Data
public class CompaniesDto {

    private Long id;

    private String companyName;

    private String address;

    @OneToOne
    private CityDto city;

    private String link;

    private String socialMediaLink;

    private String email;
}
