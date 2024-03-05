package com.example.junior_portal.api;

import com.example.junior_portal.dtos.bodies.request.*;
import com.example.junior_portal.service.chat.ChatRoomService;
import com.example.junior_portal.service.chat.ChatService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class ChatController {

    private final ChatService chatService;

    private final ChatRoomService chatRoomService;

    @MessageMapping("/process")
    public ResponseEntity<?> chatProcessMessaging(@RequestBody MessageBody messageBody){
        return chatService.processMessaging(messageBody);
    }

    @GetMapping("/countNewMessage")
    public ResponseEntity<?> countNewMessageFromChat(@RequestParam("senderId") Long senderId,
                                                  @RequestParam ("recipientId") Long recipientId){
        return chatService.countNewMessages(new NewMessage(senderId, recipientId));
    }

    @GetMapping("/findMessagesOnChat")
    public ResponseEntity<?> findMessagesOnChat(@RequestParam("senderId") Long senderId,
                                             @RequestParam ("recipientId") Long recipientId){
        return chatService.findChatMessages(new FindMessage(senderId, recipientId));
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
