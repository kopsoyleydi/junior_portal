package com.example.junior_portal.data.mapper;

import com.example.junior_portal.dtos.dto.adress.CityDto;
import com.example.junior_portal.model.address.City;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class CityMapper {
    public CityDto toDto(City city){
        CityDto cityDto = new CityDto();
        cityDto.setCode(city.getCode());
        cityDto.setId(city.getId());
        cityDto.setName(city.getName());
        return cityDto;
    };

    public City toModel(CityDto cityDto){
        City city = new City();
        city.setCode(cityDto.getCode());
        city.setId(cityDto.getId());
        city.setName(cityDto.getName());
        return city;
    };

    public List<CityDto> toDtoList(List<City> cities) {
        return cities.stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    public List<City> toModelList(List<CityDto> cityDtos) {
        return cityDtos.stream()
                .map(this::toModel)
                .collect(Collectors.toList());
    }
}
