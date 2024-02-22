package com.example.junior_portal.api;

import com.example.junior_portal.dtos.bodies.request.PassChange;
import com.example.junior_portal.dtos.response.CommonResponse;
import com.example.junior_portal.service.portal.UserService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/user")
public class UserController {

    private final UserService userService;

    @PutMapping("/pass_change")
    public ResponseEntity<?> updatePassword(@RequestBody PassChange passChange){
        return userService.changeUserPassword(passChange);
    }

    @GetMapping("/me")
    public ResponseEntity<?> getCurrentUser(@RequestHeader("Authorization") String token){
        return userService.getCurrentUser(token);
    }
}
