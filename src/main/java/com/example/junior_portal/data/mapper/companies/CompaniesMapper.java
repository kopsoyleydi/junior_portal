package com.example.junior_portal.data.mapper.companies;

import com.example.junior_portal.data.mapper.CityMapper;
import com.example.junior_portal.dtos.dto.CompaniesDto;
import com.example.junior_portal.model.company.Companies;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class CompaniesMapper {

    private final CityMapper cityMapper;
    public CompaniesDto toDto(Companies companies){
        CompaniesDto companiesDto = new CompaniesDto();
        companiesDto.setId(companies.getId());
        companiesDto.setEmail(companies.getEmail());
        companiesDto.setAddress(companiesDto.getAddress());
        companiesDto.setCompanyName(companies.getCompanyName());
        companiesDto.setLink(companies.getLink());
        companiesDto.setSocialMediaLink(companies.getSocialMediaLink());
        companiesDto.setCity(cityMapper.toDto(companies.getCity()));
        return companiesDto;
    };

    public Companies toModel(CompaniesDto companiesDto){
        Companies companies = new Companies();
        companies.setId(companiesDto.getId());
        companies.setCompanyName(companiesDto.getCompanyName());
        companies.setLink(companiesDto.getLink());
        companies.setEmail(companiesDto.getEmail());
        companies.setSocialMediaLink(companiesDto.getSocialMediaLink());
        companies.setAddress(companiesDto.getAddress());
        companies.setCity(cityMapper.toModel(companiesDto.getCity()));
        return companies;
    };

    public List<CompaniesDto> toDtoList(List<Companies> companies) {
        return companies.stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    public List<Companies> toModelList(List<CompaniesDto> companiesDtos) {
        return companiesDtos.stream()
                .map(this::toModel)
                .collect(Collectors.toList());
    }
}
