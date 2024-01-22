package com.example.junior_portal.controller;

import com.example.junior_portal.dtos.bodies.request.RegistrationBody;
import com.example.junior_portal.service.portal.UserAuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/reg")
public class RegController {

    private final UserAuthService userAuthService;

    @PostMapping("/registration")
    public ResponseEntity<?> userRegistration(@RequestBody RegistrationBody registrationBody){
        return userAuthService.createNewUser(registrationBody);
    }
}
