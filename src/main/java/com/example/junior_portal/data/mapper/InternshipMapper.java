package com.example.junior_portal.data.mapper;

import com.example.junior_portal.data.mapper.companies.CompaniesMapper;
import com.example.junior_portal.dtos.dto.InternshipDto;
import com.example.junior_portal.model.Internship;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class InternshipMapper {

    private final CompaniesMapper companiesMapper;
    public InternshipDto toDto(Internship internship){
        InternshipDto internshipDto = new InternshipDto();
        internshipDto.setId(internshipDto.getId());
        internshipDto.setActive(internship.getActive());
        internshipDto.setDescription(internship.getDescription());
        internshipDto.setTitle(internship.getTitle());
        internshipDto.setStartDate(internship.getStartDate());
        internshipDto.setEndDate(internship.getEndDate());
        internshipDto.setLinktopicture(internship.getLinktopicture());
        internshipDto.setCompaniesid(companiesMapper.toDto(internship.getCompanies()));
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
        internship.setLinktopicture(internshipDto.getLinktopicture());
        internship.setCompanies(companiesMapper.toModel(internshipDto.getCompaniesid()));
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
