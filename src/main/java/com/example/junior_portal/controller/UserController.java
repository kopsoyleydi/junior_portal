package com.example.junior_portal.controller;

import com.example.junior_portal.dtos.bodies.request.PassChange;
import com.example.junior_portal.dtos.response.CommonResponse;
import com.example.junior_portal.service.portal.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    @PutMapping("/pass_change")
    public CommonResponse updatePassword(@RequestBody PassChange passChange){
        return userService.changeUserPassword(passChange);
    }
}
