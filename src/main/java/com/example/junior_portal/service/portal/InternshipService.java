package com.example.junior_portal.service.portal;

import com.example.junior_portal.data.impl.inter.InternshipRepoInter;
import com.example.junior_portal.data.mapper.InternshipMapper;
import com.example.junior_portal.dtos.dto.InternshipDto;
import com.example.junior_portal.dtos.response.CommonResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
@Slf4j
public class InternshipService {

    private final InternshipRepoInter internshipRepoInter;

    private final InternshipMapper internshipMapper;

    private CommonResponse createInternship(InternshipDto internshipDto){
        try {
            return CommonResponse.builder()
                    .message("Internship successfully created")
                    .status(HttpStatus.CREATED)
                    .answer(internshipMapper
                            .toDto(internshipRepoInter
                                    .addInternship(internshipMapper
                                            .toModel(internshipDto))))
                    .build();
        }
        catch (Exception e){
            e.getStackTrace();
            log.info("Service: InternshipService, method: createInternship");
            return CommonResponse.builder()
                    .message("Something went wrong")
                    .status(HttpStatus.valueOf(501))
                    .build();
        }
    }

    private CommonResponse getInternshipById(Long internshipId){
        try {
            return CommonResponse.builder()
                    .message("Internship information")
                    .status(HttpStatus.ACCEPTED)
                    .answer(internshipMapper
                            .toDto(internshipRepoInter
                                    .getInternshipById(internshipId)))
                    .build();
        }
        catch (Exception e){
            e.getStackTrace();
            log.info("Service: InternshipService, method: getInternshipById");
            return CommonResponse.builder()
                    .message("Something went wrong")
                    .status(HttpStatus.valueOf(501))
                    .build();
        }
    }


    private CommonResponse getAllInternships(){
        try {
            return CommonResponse.builder()
                    .answer(internshipMapper.toDtoList(internshipRepoInter.getAllInternships()))
                    .status(HttpStatus.OK).build();
        }
        catch (Exception e){
            e.getStackTrace();
            log.info("Service: InternshipService, method: getAllInternships");
            return CommonResponse.builder()
                    .answer("Something went wrong")
                    .status(HttpStatus.valueOf(501))
                     .build();
        }

    }

    private CommonResponse changeInternship(InternshipDto internshipDto){
        try {
            return CommonResponse.builder()
                    .message("Internship successfully created")
                    .status(HttpStatus.CREATED)
                    .answer(internshipMapper
                            .toDto(internshipRepoInter
                                    .changeInternship(internshipMapper
                                            .toModel(internshipDto))))
                    .build();
        }
        catch (Exception e){
            e.getStackTrace();
            log.info("Service: InternshipService, method: changeInternship");
            return CommonResponse.builder()
                    .message("Something went wrong")
                    .status(HttpStatus.valueOf(501))
                    .build();
        }
    }

    private CommonResponse changeStatusForInternship(Long internshipId){
        try {
            return CommonResponse.builder()
                    .message("Status changed successfully")
                    .status(HttpStatus.ACCEPTED)
                    .answer(internshipRepoInter
                                    .changeStatusForInternship(internshipId))
                    .build();
        }
        catch (Exception e){
            e.getStackTrace();
            log.info("Service: InternshipService, method: changeStatusForInternship");
            return CommonResponse.builder()
                    .message("Something went wrong")
                    .status(HttpStatus.valueOf(501))
                    .build();
        }
    }



}
