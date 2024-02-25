package com.example.junior_portal.service.portal;

import com.example.junior_portal.data.impl.inter.InternshipRepoInter;
import com.example.junior_portal.data.mapper.InternshipMapper;
import com.example.junior_portal.dtos.dto.InternshipDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
@Slf4j
public class InternshipService {

    private final InternshipRepoInter internshipRepoInter;

    private final InternshipMapper internshipMapper;

    public ResponseEntity<?> createInternship(InternshipDto internshipDto){
        try {
            return ResponseEntity
                    .status(HttpStatus.CREATED)
                    .body(internshipMapper
                            .toDto(internshipRepoInter
                                    .addInternship(internshipMapper
                                            .toModel(internshipDto))));
        }
        catch (Exception e){
            e.getStackTrace();
            log.info("Service: InternshipService, method: createInternship");
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Something went wrong");
        }
    }

    public ResponseEntity<?> getInternshipById(Long internshipId){
        try {
            return ResponseEntity.
                    status(HttpStatus.ACCEPTED)
                    .body(internshipMapper
                            .toDto(internshipRepoInter
                                    .getInternshipById(internshipId)));
        }
        catch (Exception e){
            e.getStackTrace();
            log.info("Service: InternshipService, method: getInternshipById");
            return ResponseEntity
                    .status(HttpStatus.valueOf(501)).body("Something went wrong");
        }
    }


    public ResponseEntity<?> getAllInternships(){
        try {
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(internshipMapper.toDtoList(internshipRepoInter.getAllInternships()));
        }
        catch (Exception e){
            e.getStackTrace();
            log.info("Service: InternshipService, method: getAllInternships");
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Something went wrong");
        }

    }

    public ResponseEntity<?> changeInternship(InternshipDto internshipDto){
        try {
            return ResponseEntity
                    .status(HttpStatus.CREATED)
                    .body(internshipMapper
                            .toDto(internshipRepoInter
                                    .changeInternship(internshipMapper
                                            .toModel(internshipDto))));
        }
        catch (Exception e){
            e.getStackTrace();
            log.info("Service: InternshipService, method: changeInternship");
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Something went wrong");
        }
    }

    public ResponseEntity<?> changeStatusForInternship(Long internshipId){
        try {
            return ResponseEntity
                    .status(HttpStatus.ACCEPTED)
                    .body(internshipRepoInter
                                    .changeStatusForInternship(internshipId));
        }
        catch (Exception e){
            e.getStackTrace();
            log.info("Service: InternshipService, method: changeStatusForInternship");
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Something went wrong");
        }
    }



}
