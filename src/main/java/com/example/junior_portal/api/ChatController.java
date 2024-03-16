package com.example.junior_portal.api;

import com.example.junior_portal.dtos.bodies.request.NewMessage;
import com.example.junior_portal.service.chat.ChatsService;
import com.example.junior_portal.service.chat.MessageService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:3000")
public class ChatController {

    private final MessageService messageService;

    private final ChatsService chatsService;

    @CrossOrigin(origins = "http://localhost:3000")
    @MessageMapping("/send/message")
    @SendTo("/user/messages")
    public ResponseEntity<?> chatProcessMessaging(@RequestBody NewMessage newMessage){
        return messageService.processMessaging(newMessage);
    }

    @GetMapping("api/chat/findAllMessages/{userId}/{messageTo}")
    public ResponseEntity<?> getAllMessagesFromChat(@PathVariable Long userId, @PathVariable Long messageTo){
        return chatsService.getAllMessagesFromChat(userId, messageTo);
    }

    @GetMapping("api/chat/getAllChats/{userId}")
    public ResponseEntity<?> getAllChats(@PathVariable Long userId){
        return chatsService.loadAllChats(userId);
    }

    @GetMapping("/api/chat/countNewMessages/{userId}/{messageTo}/count")
    public ResponseEntity<?> countNewMessages(@PathVariable Long userId, @PathVariable Long messageTo){
        return chatsService.countNewMessages(userId, messageTo);
    }




}
