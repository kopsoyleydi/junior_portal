package com.example.junior_portal.dtos.bodies.external;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UniversityApiBody {

    String alpha_two_code;

    String state_province;

    List<String> domains;

    String name;

    List<String> webPages;

    String country;
}
