package com.example.junior_portal.controller;

import com.example.junior_portal.dtos.bodies.request.RegistrationBody;
import com.example.junior_portal.service.portal.UserAuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/reg")
public class RegController {

    private final UserAuthService userAuthService;

    @PostMapping("/registration")
    public ResponseEntity<?> userRegistration(@RequestBody RegistrationBody registrationBody){
        return userAuthService.createNewUser(registrationBody);
    }
}
