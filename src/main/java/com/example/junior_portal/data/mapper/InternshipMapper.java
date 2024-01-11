package com.example.junior_portal.data.mapper;

import com.example.junior_portal.dtos.dto.InternshipDto;
import com.example.junior_portal.model.Internship;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class InternshipMapper {
    public InternshipDto toDto(Internship internship){
        InternshipDto internshipDto = new InternshipDto();
        internshipDto.setId(internshipDto.getId());
        internshipDto.setActive(internship.getActive());
        internshipDto.setDescription(internship.getDescription());
        internshipDto.setTitle(internship.getTitle());
        internshipDto.setStartDate(internship.getStartDate());
        internshipDto.setEndDate(internship.getEndDate());
        return internshipDto;
    };

    public Internship toModel(InternshipDto internshipDto){
        Internship internship = new Internship();
        internship.setActive(internshipDto.getActive());
        internship.setId(internshipDto.getId());
        internship.setDescription(internshipDto.getDescription());
        internship.setTitle(internshipDto.getTitle());
        internship.setStartDate(internshipDto.getStartDate());
        internship.setEndDate(internshipDto.getEndDate());
        return internship;
    };

    public List<InternshipDto> toDtoList(List<Internship> internships) {
        return internships.stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    public List<Internship> toModelList(List<InternshipDto> internshipDtos) {
        return internshipDtos.stream()
                .map(this::toModel)
                .collect(Collectors.toList());
    }
}
