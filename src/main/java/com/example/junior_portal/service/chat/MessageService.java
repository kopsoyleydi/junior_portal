package com.example.junior_portal.service.chat;

import com.example.junior_portal.data.impl.inter.UserRepoInter;
import com.example.junior_portal.data.impl.inter.chat.MessageRepoInter;
import com.example.junior_portal.data.mapper.chat.MessageMapper;
import com.example.junior_portal.dtos.bodies.request.ChatRoom;
import com.example.junior_portal.dtos.bodies.request.MessageBody;
import com.example.junior_portal.dtos.bodies.request.NewMessage;
import com.example.junior_portal.dtos.dto.UserDto;
import com.example.junior_portal.dtos.dto.chat.MessageDto;
import com.example.junior_portal.model.User;
import com.example.junior_portal.model.chat.ChatMessage;
import com.example.junior_portal.model.chat.ChatNotification;
import com.example.junior_portal.model.chat.Message;
import com.example.junior_portal.model.chat.MessageStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MessageService {

    private final MessageRepoInter messageRepoInter;

    private final MessageMapper messageMapper;
    private final SimpMessagingTemplate messagingTemplate;

    private final UserRepoInter userRepoInter;

    public ResponseEntity<?> loadMessagesInChat(ChatRoom chatRoom){
        try {
            return ResponseEntity.ok(messageMapper.toDtoList(messageRepoInter.currentChatMessages(chatRoom.getCurrentUser(),
                    chatRoom.getUserFromChat())));
        }
        catch (Exception e){
            return ResponseEntity.internalServerError().body("Something went wrong");
        }
    }

    public ResponseEntity<?> processMessaging(NewMessage newMessage) {
        Message message = setterMessage(newMessage);
        messageRepoInter.sendMessage(message);
        messagingTemplate.convertAndSendToUser(String.valueOf(
                        newMessage.getMessage_to()), "/queue/messages",
                new ChatNotification(newMessage.getMessage_to()));

        return ResponseEntity.status(HttpStatus.ACCEPTED)
                .body("Message send success");
    }

    private Message setterMessage(NewMessage newMessage){
        User messageFrom = userRepoInter.findById(newMessage.getMessage_from());
        User messageTo = userRepoInter.findById(newMessage.getMessage_to());
        Message message = new Message();
        message.setId(newMessage.getId());
        message.setMessage_to(messageTo);
        message.setMessage_from(messageFrom);
        message.setContent(newMessage.getContent());
        message.setStatus(MessageStatus.DELIVERED);
        message.setCreated_at(LocalDateTime.now());
        return message;
    }


}
