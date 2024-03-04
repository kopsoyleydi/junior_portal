package com.example.junior_portal.api;

import com.example.junior_portal.dtos.dto.ProfileDto;
import com.example.junior_portal.service.portal.ProfileService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/profile")
public class ProfileController {

    private final ProfileService profileService;

    @PostMapping("/fill")
    public ResponseEntity<?> fillProfile(@RequestBody ProfileDto profileDto){
        return profileService.fillProfile(profileDto);
    }

    @GetMapping("/{email}")
    public ResponseEntity<?> getProfileByEmail(@PathVariable String email){
        return profileService.getProfileByEmail(email);
    }

    @PutMapping("/change")
    public ResponseEntity<?> changeProfile(@RequestBody ProfileDto profileDto){
        return profileService.changeProfile(profileDto);
    }
}
