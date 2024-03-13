package com.example.junior_portal.data.mapper;

import com.example.junior_portal.dtos.dto.ProfileDto;
import com.example.junior_portal.dtos.dto.adress.UniversityDto;
import com.example.junior_portal.model.Profile;
import com.example.junior_portal.model.address.University;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class UniversityMapper {

    public UniversityDto toDto(University university){
        UniversityDto universityDto = new UniversityDto();
        universityDto.setName(university.getName());
        universityDto.setCountry(university.getCountry());
        universityDto.setAlpha_two_code(university.getAlpha_two_code());
        universityDto.setState_province(university.getState_province());
        return universityDto;
    };

    public University toModel(UniversityDto universityDto){
        University university = new University();
        university.setName(universityDto.getName());
        university.setCountry(universityDto.getCountry());
        university.setAlpha_two_code(universityDto.getAlpha_two_code());
        university.setState_province(universityDto.getState_province());
        return university;
    };

    public List<UniversityDto> toDtoList(List<University> profiles) {
        return profiles.stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    public List<University> toModelList(List<UniversityDto> profileDtos) {
        return profileDtos.stream()
                .map(this::toModel)
                .collect(Collectors.toList());
    }
}
