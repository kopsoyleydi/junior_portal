package com.example.junior_portal.api;

import com.example.junior_portal.dtos.bodies.request.*;
import com.example.junior_portal.service.chat.ChatRoomService;
import com.example.junior_portal.service.chat.ChatService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:3000")
public class ChatController {

    private final ChatService chatService;

    private final ChatRoomService chatRoomService;

    @CrossOrigin(origins = "http://localhost:3000")
    @MessageMapping("/process")
    @SendTo("/user/messages")
    public ResponseEntity<?> chatProcessMessaging(@RequestBody MessageBody messageBody){
        return chatService.processMessaging(messageBody);
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @PostMapping("/addChatRoom")
    public ResponseEntity<?> addChatRoom(@RequestBody CreateRoom createRoom){
        return chatRoomService.createRoom(createRoom);
    }

    @GetMapping("api/messages/{chatId}/count")
    public int countNewMessageFromChat(@PathVariable("chatId") Long chatId){
        return chatService.countNewMessages(chatId);
    }

    @GetMapping("api/messages/findMessagesOnChat/{chatId}")
    public ResponseEntity<?> findMessagesOnChat(@PathVariable ("chatId") Long chatId){
        return chatService.findChatMessages(chatId);
    }

    @PutMapping("/updateStatus")
    public ResponseEntity<?> updateStatusMessages(@RequestBody UpdateStatuses updateStatuses){
        return chatService.updateStatuses(updateStatuses);
    }

    @PostMapping("/createRoom")
    public ResponseEntity<?> createRoom(@RequestBody CreateRoom createRoom){
        return chatRoomService.createRoom(createRoom);
    }


}
