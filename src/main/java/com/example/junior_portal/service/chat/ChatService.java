package com.example.junior_portal.service.chat;


import com.example.junior_portal.data.impl.inter.ChatMessageRepoInter;
import com.example.junior_portal.data.impl.inter.ChatRoomRepoInter;
import com.example.junior_portal.data.impl.inter.UserRepoInter;
import com.example.junior_portal.data.mapper.chat.ChatMessageMapper;
import com.example.junior_portal.dtos.bodies.request.MessageBody;
import com.example.junior_portal.dtos.bodies.request.UpdateStatuses;

import com.example.junior_portal.model.chat.ChatMessage;
import com.example.junior_portal.model.chat.ChatNotification;
import com.example.junior_portal.model.chat.ChatRoom;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class ChatService {

    private final ChatMessageRepoInter chatMessageInter;

    private final UserRepoInter userRepoInter;

    private final ChatMessageMapper chatMessageMapper;

    private final SimpMessagingTemplate messagingTemplate;

    private final ChatRoomRepoInter chatRoomRepoInter;

    public ResponseEntity<?> processMessaging(MessageBody messageBody) {
        ChatMessage chatMessage = setterChatMessage(messageBody);
        ChatRoom chatRoom = chatRoomRepoInter.getChatRoom(messageBody.getChatId());
        List<ChatMessage> messages = chatRoom.getMessages();
        messages.add(chatMessage);
        chatRoom.setMessages(messages);
        messagingTemplate.convertAndSendToUser(String.valueOf(
                chatRoom.getId()), "/queue/messages",
                new ChatNotification(chatRoom.getId()));

        return ResponseEntity.status(HttpStatus.ACCEPTED)
                .body("Message send success");
    }


    @Deprecated
    private ResponseEntity<?> addNewMessage(MessageBody messageBody) {
        try {
            ChatMessage chatMessage = setterChatMessage(messageBody);
            chatMessageInter.create(chatMessage);
            return ResponseEntity
                    .status(HttpStatus.CREATED).body("Message send successfully");
        } catch (Exception e) {
            log.info("Service: ChatService, method: addNewMessages");
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR).body("Something went wrong");
        }

    }

    private ChatMessage setterChatMessage(MessageBody messageBody) {
        ChatMessage chatMessage = new ChatMessage();
        chatMessage.setSenderId(messageBody.getSenderId());
        chatMessage.setChatId(messageBody.getChatId());
        chatMessage.setSenderName(userRepoInter.findById(messageBody.getSenderId()).getUsername());
        chatMessage.setContent(messageBody.getContent());
        chatMessage.setTimestamp(Instant.now());
        return chatMessage;
    }

    public int countNewMessages(Long chatId) {
        try {
            return chatMessageInter.countNewMessages(chatId);
        } catch (Exception e) {
            e.getStackTrace();
            log.info("Service: ChatService, method: countNewMessages");
            return 0;
        }
    }

    public ResponseEntity<?> findChatAndCreateRoomMessages(Long chatId, Long user1, Long user2) {
        try {

            ChatRoom chatRoom = chatRoomRepoInter.addChatRoom(user1, user2);

            List<ChatMessage> messages = chatRoom.getMessages();
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(chatMessageMapper.toDtoList(messages));
        } catch (Exception e) {
            log.info("Service: ChatService, method: findChatMessages");
            e.getStackTrace();
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Something went wrong");
        }
    }

    public ResponseEntity<?> findChatMessages(Long chatId) {
        try {

            ChatRoom chatRoom = chatRoomRepoInter.getChatRoom(chatId);

            List<ChatMessage> messages = chatRoom.getMessages();
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(chatMessageMapper.toDtoList(messages));
        } catch (Exception e) {
            log.info("Service: ChatService, method: findChatMessages");
            e.getStackTrace();
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Something went wrong");
        }
    }

    public ResponseEntity<?> updateStatuses(UpdateStatuses updateStatuses) {
        try {
            chatMessageInter.updateStatuses(
                    updateStatuses.getSenderId(),
                    updateStatuses.getRecipientId(),
                    updateStatuses.getMessageStatus());
            return ResponseEntity.status(HttpStatus.OK)
                    .body("Message status updated");
        } catch (Exception e) {
            e.getStackTrace();
            log.info("Service: ChatService, method: updateStatuses");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Something went wrong");
        }
    }

}
