package com.example.junior_portal.data.mapper;

import com.example.junior_portal.data.impl.inter.UserRepoInter;
import com.example.junior_portal.dtos.dto.ProfileDto;
import com.example.junior_portal.model.Profile;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class ProfileMapper {

    private final UserMapper userMapper;

    private final UserRepoInter userRepoInter;

    public ProfileDto toDto(Profile profile){
        ProfileDto profileDto = new ProfileDto();
        profileDto.setId(profileDto.getId());
        profileDto.setBio(profile.getBio());
        profileDto.setName(profile.getName());

        profileDto.setUserId(userMapper.toDto(profile.getUserId()));
        profileDto.setUniversity(profile.getUniversity());
        profileDto.setExperience(profile.getExperience());
        return profileDto;
    };

    public Profile toModel(ProfileDto profileDto){
        Profile profile = new Profile();
        profile.setId(profileDto.getId());
        profile.setBio(profileDto.getBio());
        profile.setName(profileDto.getName());
        profile.setUniversity(profileDto.getUniversity());
        profile.setExperience(profileDto.getExperience());
        profile.setUserId(userMapper.toModel(profileDto.getUserId()));
        return profile;
    };

    public List<ProfileDto> toDtoList(List<Profile> profiles) {
        return profiles.stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    public List<Profile> toModelList(List<ProfileDto> profileDtos) {
        return profileDtos.stream()
                .map(this::toModel)
                .collect(Collectors.toList());
    }
}
