package com.example.junior_portal.service.chat;


import com.example.junior_portal.data.impl.inter.ChatMessageRepoInter;
import com.example.junior_portal.data.impl.inter.ChatRoomRepoInter;
import com.example.junior_portal.data.impl.inter.UserRepoInter;
import com.example.junior_portal.data.mapper.chat.ChatMessageMapper;
import com.example.junior_portal.dtos.bodies.FindMessage;
import com.example.junior_portal.dtos.bodies.MessageBody;
import com.example.junior_portal.dtos.bodies.NewMessage;
import com.example.junior_portal.dtos.response.CommonResponse;
import com.example.junior_portal.model.User;
import com.example.junior_portal.model.chat.ChatMessage;
import com.example.junior_portal.model.chat.ChatNotification;
import com.example.junior_portal.model.chat.MessageStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ChatService {

    private final ChatMessageRepoInter chatMessageInter;

    private final UserRepoInter userRepoInter;

    private final ChatMessageMapper chatMessageMapper;

    private final SimpMessagingTemplate messagingTemplate;

    private final ChatRoomRepoInter chatRoomRepoInter;

    public CommonResponse processMessaging(@Payload MessageBody messageBody){
        ChatMessage chatMessage = setterChatMessage(messageBody);
        chatMessage.setChatId(chatRoomRepoInter
                .getChatRoom(chatMessage.getSenderId(), chatMessage.getRecipientId()).getChatId());

        ChatMessage saved = chatMessageInter.create(chatMessage);

        messagingTemplate.convertAndSendToUser(String.valueOf(chatMessage.getRecipientId()), "/queue/messages",
                new ChatNotification(saved.getId(), saved.getSenderId(), saved.getSenderName()));

        return CommonResponse.builder()
                .message("Message send success")
                .status(HttpStatus.ACCEPTED).build();
    }



    private CommonResponse addNewMessage(MessageBody messageBody){
        try {
            ChatMessage chatMessage = setterChatMessage(messageBody);
            chatMessageInter.create(chatMessage);
            return CommonResponse.builder().message("Message send successfully").status(HttpStatus.CREATED).build();
        }
        catch (Exception e){
            return CommonResponse.builder().message("Something went wrong").status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }

    }

    private ChatMessage setterChatMessage(MessageBody messageBody){
        ChatMessage chatMessage = new ChatMessage();
        User sender = userRepoInter.getUserByEmail(messageBody.getSenderEmail());
        chatMessage.setSenderId(sender.getId());
        chatMessage.setSenderName(sender.getUsername());
        User recipient = userRepoInter.getUserByEmail(messageBody.getRecipientEmail());
        chatMessage.setRecipientId(recipient.getId());
        chatMessage.setRecipientName(recipient.getUsername());
        chatMessage.setContent(messageBody.getContent());
        chatMessage.setTimestamp(Instant.now());
        return chatMessage;
    }

    private CommonResponse countNewMessages(NewMessage newMessage){
        try {
            List<ChatMessage> countNewMessages = chatMessageInter.countNewMessages(newMessage.getSenderId()
                    , newMessage.getRecipientId());
            long countMessages = countNewMessages.size();
            return CommonResponse.builder()
                    .answer(countMessages).message("You have " + countMessages + " new " +
                            ((countMessages > 1)?"messages":"message"))
                    .status(HttpStatus.OK).build();
        }
        catch (Exception e){
            e.getStackTrace();
            return CommonResponse.builder()
                    .message("Something went wrong").status(HttpStatus.valueOf(501)).build();
        }
    }

    private CommonResponse findChatMessages(FindMessage findMessage){
        try {
            List<ChatMessage> messages = chatMessageInter.findChatMessages(
                    findMessage.getSenderId(),
                    findMessage.getRecipientId()
            );
            return CommonResponse.builder()
                    .answer(chatMessageMapper.toDtoList(messages))
                    .message("Messages")
                    .status(HttpStatus.OK).build();
        }
        catch (Exception e){
            return CommonResponse.builder()
                    .answer("No messages")
                    .message("Something new wrong")
                    .status(HttpStatus.valueOf(501)).build();
        }
    }

    private CommonResponse updateStatuses(Long senderId, Long recipientId, MessageStatus messageStatus){
        try {
            chatMessageInter.updateStatuses(senderId, recipientId, messageStatus);
            return CommonResponse.builder()
                    .message("Message status updated").status(HttpStatus.OK)
                    .build();
        }
        catch (Exception e){
            e.getStackTrace();
            return CommonResponse.builder()
                    .message("Something went wrong").status(HttpStatus.valueOf(501))
                    .build();
        }
    }

}
