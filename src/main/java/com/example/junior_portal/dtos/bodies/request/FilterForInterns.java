package com.example.junior_portal.dtos.bodies.request;

import com.example.junior_portal.enums.FilterInternByEmploymentType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class FilterForInterns {

    private String university;

    private String city;

    private FilterInternByEmploymentType employmentType;
}
