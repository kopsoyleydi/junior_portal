package com.example.junior_portal.service.external;


import com.example.junior_portal.dtos.bodies.external.UniversityApiBody;
import com.example.junior_portal.dtos.dto.UserDto;
import com.example.junior_portal.external.UniversityAPI;
import com.example.junior_portal.model.User;
import com.example.junior_portal.model.address.University;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class UniversityService {

    private final UniversityAPI universityAPI;

    public ResponseEntity<?> selectUniversitiesByCountryAndCity(String name, String country){
        ResponseEntity<UniversityApiBody[]> universities = universityAPI.findUniversitiesByNameAndCountry(name, country);
        List<UniversityApiBody> universityApiBodies = List.of(Objects.requireNonNull(universities.getBody()));
        List<University> universityList = toList(universityApiBodies);
        return ResponseEntity.ok(universityList);
    }

    public University toModel(UniversityApiBody universityApiBody){
        University university = new University();
        university.setName(universityApiBody.getName());
        university.setCountry(universityApiBody.getCountry());
        university.setState_province(universityApiBody.getState_province());
        university.setAlpha_two_code(university.getAlpha_two_code());
        return university;
    };


    public List<University> toList(List<UniversityApiBody> universityApiBodies) {
        return universityApiBodies.stream()
                .map(this::toModel)
                .collect(Collectors.toList());
    }
}
