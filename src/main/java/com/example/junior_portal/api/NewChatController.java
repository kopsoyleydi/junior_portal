package com.example.junior_portal.api;

import com.example.junior_portal.service.chat.ChatService;
import com.example.junior_portal.service.chat.MessageService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class NewChatController {

    private final MessageService messageService;

    private final ChatService chatService;
}
