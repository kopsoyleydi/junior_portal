package com.example.junior_portal.api;

import com.example.junior_portal.dtos.bodies.request.PassChange;
import com.example.junior_portal.dtos.response.CommonResponse;
import com.example.junior_portal.service.portal.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/user")
public class UserController {

    private final UserService userService;

    @PutMapping("/pass_change")
    public CommonResponse updatePassword(@RequestBody PassChange passChange){
        return userService.changeUserPassword(passChange);
    }
}
