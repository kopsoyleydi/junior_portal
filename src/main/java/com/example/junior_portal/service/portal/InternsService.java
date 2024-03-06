package com.example.junior_portal.service.portal;

import com.example.junior_portal.data.impl.inter.ProfileRepoInter;
import com.example.junior_portal.data.mapper.ProfileMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class InternsService {

    private final ProfileRepoInter profileRepoInter;

    private final ProfileMapper profileMapper;

    public ResponseEntity<?> getAllInterns(){
        try {
            return ResponseEntity.status(HttpStatus.OK)
                    .body(profileMapper.toDtoList(
                            profileRepoInter.getAllProfiles()));
        }
        catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Something went wrong");
        }
    }
}
