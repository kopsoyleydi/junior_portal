package com.example.junior_portal.api;

import com.example.junior_portal.model.Profile;
import com.example.junior_portal.util.EmailSender;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/email")
public class EmailController {

    private final EmailSender emailService;

    @PostMapping("/sendCV/{email}")
    public ResponseEntity<?> sendCV(@PathVariable String email){
        return ResponseEntity.ok(emailService.sendEmailWithCV(email, "CV", new Profile()));
    }
}
