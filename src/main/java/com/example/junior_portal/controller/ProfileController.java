package com.example.junior_portal.controller;

import com.example.junior_portal.dtos.dto.ProfileDto;
import com.example.junior_portal.dtos.response.CommonResponse;
import com.example.junior_portal.service.portal.ProfileService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
@RequestMapping("/profile")
public class ProfileController {

    private final ProfileService profileService;

    @PostMapping("/fill")
    public CommonResponse fillProfile(@RequestBody ProfileDto profileDto){
        return profileService.fillProfile(profileDto);
    }

    @GetMapping("/{email}")
    public CommonResponse getProfileByEmail(@PathVariable String email){
        return profileService.getProfileByEmail(email);
    }

    @PutMapping("/change")
    public CommonResponse changeProfile(@RequestBody ProfileDto profileDto){
        return profileService.changeProfile(profileDto);
    }
}
