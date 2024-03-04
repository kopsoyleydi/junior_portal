package com.example.junior_portal.dtos.dto;

import com.example.junior_portal.model.company.Companies;
import jakarta.persistence.OneToOne;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class InternshipDto {
    private Long id;

    private String title;

    private String description;

    private String startDate;

    private String endDate;

    private Boolean active;

    private String linktopicture;
    @OneToOne
    private CompaniesDto companiesDto;
}
