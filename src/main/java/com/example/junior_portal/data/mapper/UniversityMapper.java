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
        universityDto.setCity(universityDto.getCity());
        universityDto.setStreet(universityDto.getStreet());
        universityDto.setWebSite(universityDto.getWebSite());
        universityDto.setBuildingNumber(universityDto.getBuildingNumber());
        universityDto.setName(universityDto.getName());
        universityDto.setId(university.getId());
        return universityDto;
    };

    public University toModel(UniversityDto universityDto){
        University university = new University();
        university.setCity(university.getCity());
        university.setStreet(universityDto.getStreet());
        university.setWebSite(universityDto.getWebSite());
        university.setBuildingNumber(universityDto.getBuildingNumber());
        university.setName(universityDto.getName());
        university.setId(university.getId());
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
