package com.example.junior_portal.data.mapper;

import com.example.junior_portal.dtos.dto.ProfileDto;
import com.example.junior_portal.model.Profile;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ProfileMapper {
    ProfileDto toDto(Profile profile);

    Profile toModel(ProfileDto profileDto);

    List<ProfileDto> toDtoList(List<Profile> list);

    List<Profile> toModelList(List<ProfileDto> list);
}
