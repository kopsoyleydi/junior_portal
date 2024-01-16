package com.example.junior_portal.service.chat;


import com.example.junior_portal.data.impl.inter.ChatMessageRepoInter;
import com.example.junior_portal.data.impl.inter.UserRepoInter;
import com.example.junior_portal.data.mapper.chat.ChatMessageMapper;
import com.example.junior_portal.dtos.bodies.FindMessage;
import com.example.junior_portal.dtos.bodies.MessageBody;
import com.example.junior_portal.dtos.bodies.NewMessage;
import com.example.junior_portal.dtos.response.CommonResponse;
import com.example.junior_portal.model.User;
import com.example.junior_portal.model.chat.ChatMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ChatService {

    private final ChatMessageRepoInter chatMessageInter;

    private final UserRepoInter userRepoInter;

    private ChatMessageMapper chatMessageMapper;


    private CommonResponse addNewMessage(MessageBody messageBody){
        try {
            ChatMessage chatMessage = new ChatMessage();
            User sender = userRepoInter.getUserByEmail(messageBody.getSenderEmail());
            chatMessage.setSenderId(sender.getId());
            chatMessage.setSenderName(sender.getUsername());
            User recipient = userRepoInter.getUserByEmail(messageBody.getRecipientEmail());
            chatMessage.setRecipientId(recipient.getId());
            chatMessage.setRecipientName(recipient.getUsername());
            chatMessage.setContent(messageBody.getContent());
            chatMessage.setTimestamp(Instant.now());
            chatMessageInter.create(chatMessage);
            return CommonResponse.builder().message("Message send successfully").status(HttpStatus.CREATED).build();
        }
        catch (Exception e){
            return CommonResponse.builder().message("Something went wrong").status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }

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

}
