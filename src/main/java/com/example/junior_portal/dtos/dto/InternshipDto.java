package com.example.junior_portal.dtos.dto;

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
}
