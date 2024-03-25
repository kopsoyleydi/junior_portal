package com.example.junior_portal.service.portal;

import com.example.junior_portal.data.impl.inter.ProfileRepoInter;
import com.example.junior_portal.data.impl.inter.UserRepoInter;
import com.example.junior_portal.model.Profile;
import com.example.junior_portal.util.EmailSender;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmailService {

    private final EmailSender emailSender;

    private final UserService userService;

    private final ProfileRepoInter profileService;


    public boolean sendCVToEmail(String token){
        String to = userService.currentUser(token).getEmail();
        Profile profile = profileService.getProfileByEmail(to);
        return emailSender.sendEmailWithCV(to, "CV", profile);
    }
}
