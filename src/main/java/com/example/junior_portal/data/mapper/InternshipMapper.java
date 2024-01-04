package com.example.junior_portal.data.mapper;

import com.example.junior_portal.dtos.dto.InternshipDto;
import com.example.junior_portal.model.Internship;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface InternshipMapper {
    InternshipDto toDto(Internship internship);

    Internship toModel(InternshipDto internshipDto);

    List<InternshipDto> toDtoList(List<Internship> list);

    List<Internship> toModelList(List<InternshipDto> list);
}
