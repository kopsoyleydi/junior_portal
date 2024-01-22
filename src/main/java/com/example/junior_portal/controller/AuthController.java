package com.example.junior_portal.controller;

import com.example.junior_portal.dtos.bodies.request.AuthRequest;
import com.example.junior_portal.service.portal.UserAuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/auth")
public class AuthController {

    private final UserAuthService userAuthService;

    @PostMapping("/login")
    public ResponseEntity<?> authUser(@RequestBody AuthRequest authRequest){
        return userAuthService.createAuthToken(authRequest);
    }
}
