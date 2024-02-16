package com.example.junior_portal.api;

import com.example.junior_portal.dtos.bodies.request.RegistrationBody;
import com.example.junior_portal.service.portal.UserAuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("basic/reg")
public class RegController {

    private final UserAuthService userAuthService;

    @PostMapping("/registration")
    public ResponseEntity<?> userRegistration(@RequestBody RegistrationBody registrationBody){
        return userAuthService.createNewUser(registrationBody);
    }
}
