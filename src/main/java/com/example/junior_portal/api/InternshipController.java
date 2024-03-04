package com.example.junior_portal.api;

import com.example.junior_portal.dtos.dto.InternshipDto;
import com.example.junior_portal.service.portal.InternshipService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/internship")
public class InternshipController {

    private final InternshipService internshipService;

    @PostMapping("/create")
    public ResponseEntity<?> createInternship(@RequestBody InternshipDto internshipDto){
        return internshipService.createInternship(internshipDto);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getInternshipById(@PathVariable Long id){
        return internshipService.getInternshipById(id);
    }

    @PostMapping("/change_status/{id}")
    public ResponseEntity<?> changeStatusInternship(@PathVariable Long id){
        return internshipService.changeStatusForInternship(id);
    }

    @GetMapping("/all")
    public ResponseEntity<?> getAllInternships(){
        return internshipService.getAllInternships();
    }


}
