package com.example.junior_portal.controller;

import com.example.junior_portal.dtos.bodies.FindMessage;
import com.example.junior_portal.dtos.bodies.MessageBody;
import com.example.junior_portal.dtos.bodies.NewMessage;
import com.example.junior_portal.dtos.bodies.UpdateStatuses;
import com.example.junior_portal.dtos.response.CommonResponse;
import com.example.junior_portal.service.chat.ChatService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
@RequestMapping("/api/chat")
public class ChatController {

    private final ChatService chatService;

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


}
