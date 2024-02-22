package com.example.junior_portal.service.portal;

import com.example.junior_portal.data.impl.inter.ProfileRepoInter;
import com.example.junior_portal.data.mapper.ProfileMapper;
import com.example.junior_portal.dtos.dto.ProfileDto;
import com.example.junior_portal.dtos.response.CommonResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProfileService {

    private final ProfileRepoInter profileRepoInter;

    private final ProfileMapper profileMapper;

    public ResponseEntity<?> fillProfile(ProfileDto profileDto){
        try {
            return ResponseEntity.status(HttpStatus.OK)
                    .body(profileRepoInter.createProfile(profileMapper.toModel(profileDto)));
        }
        catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Something went wrong");
        }
    }

    public ResponseEntity<?> getProfileByEmail(String email){
        try {
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(profileRepoInter.getProfileByEmail(email));
        }
        catch (Exception e){
            e.getStackTrace();
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body("Something went wrong");
        }
    }

    public ResponseEntity<?> changeProfile(ProfileDto profileDto){
        try {
            return ResponseEntity.status(HttpStatus.OK)
                    .body( profileRepoInter.changeProfile(profileMapper.toModel(profileDto)));
        }
        catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Something went wrong");
        }
    }


}
