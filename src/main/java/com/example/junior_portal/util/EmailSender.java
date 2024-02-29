package com.example.junior_portal.util;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;


@Component
@Slf4j
public class EmailSender {

    @Autowired
    private JavaMailSender javaMailSender;


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

}
