package com.example.junior_portal.service.chat;

import com.example.junior_portal.data.impl.inter.chat.MessageRepoInter;
import com.example.junior_portal.data.mapper.chat.MessageMapper;
import com.example.junior_portal.dtos.bodies.request.ChatRoom;
import com.example.junior_portal.dtos.bodies.request.MessageBody;
import com.example.junior_portal.dtos.bodies.request.NewMessage;
import com.example.junior_portal.dtos.dto.chat.MessageDto;
import com.example.junior_portal.model.chat.ChatMessage;
import com.example.junior_portal.model.chat.ChatNotification;
import com.example.junior_portal.model.chat.Message;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MessageService {

    private final MessageRepoInter messageRepoInter;

    private final MessageMapper messageMapper;
    private final SimpMessagingTemplate messagingTemplate;

    public ResponseEntity<?> loadMessagesInChat(ChatRoom chatRoom){
        try {
            return ResponseEntity.ok(messageMapper.toDtoList(messageRepoInter.currentChatMessages(chatRoom.getCurrentUser(),
                    chatRoom.getUserFromChat())));
        }
        catch (Exception e){
            return ResponseEntity.internalServerError().body("Something went wrong");
        }
    }

    public ResponseEntity<?> processMessaging(MessageDto messageDto) {
        Message message = messageMapper.toModel(messageDto);
        messageRepoInter.sendMessage(message);
        messagingTemplate.convertAndSendToUser(String.valueOf(
                        messageDto.getMessage_to().getId()), "/queue/messages",
                new ChatNotification(messageDto.getMessage_to().getId()));

        return ResponseEntity.status(HttpStatus.ACCEPTED)
                .body("Message send success");
    }


}
