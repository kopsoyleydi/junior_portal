package com.example.junior_portal.service.portal;

import com.example.junior_portal.data.impl.inter.ProfileRepoInter;
import com.example.junior_portal.data.mapper.ProfileMapper;
import com.example.junior_portal.dtos.dto.ProfileDto;
import com.example.junior_portal.dtos.response.CommonResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProfileService {

    private final ProfileRepoInter profileRepoInter;

    private final ProfileMapper profileMapper;

    private CommonResponse createProfile(ProfileDto profileDto){
        try {
            return CommonResponse.builder()
                    .message("Profile created")
                    .status(HttpStatus.CREATED)
                    .answer(profileRepoInter.createProfile(profileMapper.toModel(profileDto))).build();
        }
        catch (Exception e){
            return CommonResponse.builder()
                    .message("Something went wrong")
                    .status(HttpStatus.valueOf(501)).build();
        }
    }

    private CommonResponse getProfileByEmail(String email){
        try {
            return CommonResponse.builder()
                    .answer(profileRepoInter.getProfileByEmail(email))
                    .status(HttpStatus.OK).build();
        }
        catch (Exception e){
            e.getStackTrace();
            return CommonResponse.builder()
                    .status(HttpStatus.valueOf(501))
                    .message("Something went wrong")
                    .build();
        }
    }
}
