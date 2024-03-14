package com.example.junior_portal.service.chat;

import com.example.junior_portal.data.impl.inter.chat.MessageRepoInter;
import com.example.junior_portal.data.mapper.chat.MessageMapper;
import com.example.junior_portal.dtos.bodies.request.ChatRoom;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MessageService {

    private final MessageRepoInter messageRepoInter;

    private final MessageMapper messageMapper;


    public ResponseEntity<?> loadMessagesInChat(ChatRoom chatRoom){
        try {
            return ResponseEntity.ok(messageMapper.toDtoList(messageRepoInter.currentChatMessages(chatRoom.getCurrentUser(),
                    chatRoom.getUserFromChat())));
        }
        catch (Exception e){
            return ResponseEntity.internalServerError().body("Something went wrong");
        }
    }

}
