package com.example.junior_portal.api;

import com.example.junior_portal.model.Profile;
import com.example.junior_portal.service.portal.EmailService;
import com.example.junior_portal.util.EmailSender;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/email")
public class EmailController {

    private final EmailService emailService;

    @PostMapping("/sendCV")
    public ResponseEntity<?> sendCV(@RequestHeader("Authorization") String token){
        try {
            return ResponseEntity.ok(emailService.sendCVToEmail(token));
        } catch (Exception e){
            return ResponseEntity.badRequest().body("Error while sending CV");
        }
    }
}
