package com.example.junior_portal.api;

import com.example.junior_portal.service.portal.InternsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("api/interns")
@CrossOrigin(origins = "http://localhost:3000")

public class InternsControllers {

    private final InternsService internsService;

    @GetMapping("/all")
    public ResponseEntity<?> getAllInterns(){
        return internsService.getAllInterns();
    }
}
