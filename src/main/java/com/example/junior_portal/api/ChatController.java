package com.example.junior_portal.api;

import com.example.junior_portal.dtos.bodies.request.NewMessage;
import com.example.junior_portal.dtos.dto.chat.MessageDto;
import com.example.junior_portal.service.chat.ChatsService;
import com.example.junior_portal.service.chat.MessageService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class ChatController {

    private final ChatsService chatsService;


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

    @GetMapping("/api/chat/findChatMessageByIds/{userId}/{messageTo}")
    public ResponseEntity<?> findChatMessageByIds(@PathVariable Long userId, @PathVariable Long messageTo){
        return chatsService.findChatMessageByIds(userId, messageTo);
    }




}
