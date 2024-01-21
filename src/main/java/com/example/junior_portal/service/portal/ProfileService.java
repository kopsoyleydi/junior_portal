package com.example.junior_portal.service.portal;

import com.example.junior_portal.data.impl.inter.ProfileRepoInter;
import com.example.junior_portal.data.mapper.ProfileMapper;
import com.example.junior_portal.dtos.dto.ProfileDto;
import com.example.junior_portal.dtos.response.CommonResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProfileService {

    private final ProfileRepoInter profileRepoInter;

    private final ProfileMapper profileMapper;

    public CommonResponse fillProfile(ProfileDto profileDto){
        try {
            return CommonResponse.builder()
                    .message("Profile fill")
                    .status(HttpStatus.OK)
                    .answer(profileRepoInter.createProfile(profileMapper.toModel(profileDto))).build();
        }
        catch (Exception e){
            return CommonResponse.builder()
                    .message("Something went wrong")
                    .status(HttpStatus.valueOf(501)).build();
        }
    }

    public CommonResponse getProfileByEmail(String email){
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

    public CommonResponse changeProfile(ProfileDto profileDto){
        try {
            return CommonResponse.builder()
                    .answer( profileRepoInter.changeProfile(profileMapper.toModel(profileDto)))
                    .status(HttpStatus.OK)
                    .message("Profile change successfully").build();
        }
        catch (Exception e){
            return CommonResponse.builder()
                    .status(HttpStatus.valueOf(501))
                    .message("Something went wrong").build();
        }
    }


}
