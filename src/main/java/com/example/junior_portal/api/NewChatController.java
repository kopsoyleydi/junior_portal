package com.example.junior_portal.api;

import com.example.junior_portal.dtos.bodies.request.MessageBody;
import com.example.junior_portal.dtos.bodies.request.NewMessage;
import com.example.junior_portal.dtos.dto.chat.MessageDto;
import com.example.junior_portal.service.chat.ChatService;
import com.example.junior_portal.service.chat.ChatsService;
import com.example.junior_portal.service.chat.MessageService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:3000")
public class NewChatController {

    private final MessageService messageService;

    private final ChatsService chatsService;

    @CrossOrigin(origins = "http://localhost:3000")
    @MessageMapping("/send/message")
    @SendTo("/user/messages")
    public ResponseEntity<?> chatProcessMessaging(@RequestBody NewMessage newMessage){
        return messageService.processMessaging(newMessage);
    }


}
