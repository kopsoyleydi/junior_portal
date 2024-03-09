package com.example.junior_portal.api;

import com.example.junior_portal.dtos.bodies.request.AuthRequest;
import com.example.junior_portal.service.portal.UserAuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("basic/auth")
@CrossOrigin(origins = "http://localhost:3000")

public class AuthController {

    private final UserAuthService userAuthService;

    @PostMapping("/login")
    public ResponseEntity<?> authUser(@RequestBody AuthRequest authRequest){
        return userAuthService.createAuthToken(authRequest);
    }
}
