package com.example.junior_portal.dtos.bodies.api;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class UniversityBody {

    private String name;

    private Long cityId;

    private String description;

}
