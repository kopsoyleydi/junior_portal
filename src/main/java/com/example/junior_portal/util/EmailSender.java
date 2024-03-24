package com.example.junior_portal.util;

import com.example.junior_portal.dtos.dto.UserDto;
import com.example.junior_portal.model.Profile;
import com.example.junior_portal.service.documents.PDFDocument;
import com.example.junior_portal.service.documents.PDFGenerator;
import jakarta.mail.internet.MimeMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.InputStreamSource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;


@Component
@Slf4j
public class EmailSender {

    @Autowired
    private JavaMailSender javaMailSender;

    @Autowired
    private PDFDocument pdfDocument;


    public boolean sendNotificationFromAccept(String to, String subject, String text){
        try {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setFrom("kuralbaibexultan.com");
            message.setTo(to);
            message.setSubject(subject);
            message.setText(text);
            javaMailSender.send(message);
            return true;
        }
        catch (Exception e){
            log.info("Exception in sendNotificationFromAccept");
            e.getMessage();
            return false;
        }

    }

    public boolean sendNotificationAboutNews(String to, String subject, String text) {
        try {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setFrom("kuralbaibexultan.com");
            message.setTo(to);
            message.setSubject("This is from Junior Portal "+  subject);
            message.setText(text);
            javaMailSender.send(message);
            return true;
        }
        catch (Exception e){
            log.info("Exception in sendNotificationFromAccept");
            e.getMessage();
            return false;
        }
    }

    public boolean sendEmailMessage(UserDto user, String to, String subject, String text) {
        try {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setFrom(user.getEmail());
            message.setTo(to);
            message.setSubject("This is from Junior Portal "+  subject);
            message.setText(text);
            javaMailSender.send(message);
            return true;
        }
        catch (Exception e){
            log.info("Exception in sendNotificationFromAccept");
            e.getMessage();
            return false;
        }
    }

    public boolean sendEmailWithCV(String toEmail, String subject, Profile profile){
        try {
            MimeMessage message = javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setTo(toEmail);
            helper.setSubject(subject);
            byte[] pdfBytes = pdfDocument.createPDFForCV(profile);
            helper.setText("Текст вашего сообщения");

            helper.addAttachment("document.pdf", new ByteArrayResource(pdfBytes));
            javaMailSender.send(message);
            return true;
        }
        catch (Exception e){
            log.info("Exception in sendEmailWithCV");
            e.getMessage();
            return false;
        }
    }

}
