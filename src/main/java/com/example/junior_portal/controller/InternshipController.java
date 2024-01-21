package com.example.junior_portal.controller;

import com.example.junior_portal.dtos.dto.InternshipDto;
import com.example.junior_portal.dtos.response.CommonResponse;
import com.example.junior_portal.service.portal.InternshipService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
@RequestMapping("/internship")
public class InternshipController {

    private final InternshipService internshipService;

    @PostMapping("/create")
    public CommonResponse createInternship(@RequestBody InternshipDto internshipDto){
        return internshipService.createInternship(internshipDto);
    }

    @GetMapping("/{id}")
    public CommonResponse getInternshipById(@PathVariable Long id){
        return internshipService.getInternshipById(id);
    }

    @PostMapping("/change_status/{id}")
    public CommonResponse changeStatusInternship(@PathVariable Long id){
        return internshipService.changeStatusForInternship(id);
    }

    @GetMapping("/all")
    public CommonResponse getAllInternships(){
        return internshipService.getAllInternships();
    }


}
