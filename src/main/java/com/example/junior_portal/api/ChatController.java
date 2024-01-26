package com.example.junior_portal.api;

import com.example.junior_portal.dtos.bodies.request.*;
import com.example.junior_portal.dtos.response.CommonResponse;
import com.example.junior_portal.service.chat.ChatRoomService;
import com.example.junior_portal.service.chat.ChatService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/chat")
public class ChatController {

    private final ChatService chatService;

    private final ChatRoomService chatRoomService;

    @PostMapping("/process")
    public CommonResponse chatProcessMessaging(@RequestBody MessageBody messageBody){
        return chatService.processMessaging(messageBody);
    }

    @GetMapping("/countNewMessage")
    public CommonResponse countNewMessageFromChat(@RequestParam("senderId") Long senderId,
                                                  @RequestParam ("recipientId") Long recipientId){
        return chatService.countNewMessages(new NewMessage(senderId, recipientId));
    }

    @GetMapping("/findMessagesOnChat")
    public CommonResponse findMessagesOnChat(@RequestParam("senderId") Long senderId,
                                             @RequestParam ("recipientId") Long recipientId){
        return chatService.findChatMessages(new FindMessage(senderId, recipientId));
    }

    @PutMapping("/updateStatus")
    public CommonResponse updateStatusMessages(@RequestBody UpdateStatuses updateStatuses){
        return chatService.updateStatuses(updateStatuses);
    }

    @PostMapping("/createRoom")
    public CommonResponse createRoom(@RequestBody CreateRoom createRoom){
        return chatRoomService.createRoom(createRoom);
    }


}