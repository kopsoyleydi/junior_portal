package com.example.junior_portal.api;

import com.example.junior_portal.dtos.bodies.request.PassChange;
import com.example.junior_portal.service.portal.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/user")
@CrossOrigin(origins = "http://localhost:3000")
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

    @GetMapping("/allusers")
    public ResponseEntity<?> getUsers(@RequestHeader("Authorization") String token){
        return userService.findAllUsersWithoutCurrent(token);
    }
}
