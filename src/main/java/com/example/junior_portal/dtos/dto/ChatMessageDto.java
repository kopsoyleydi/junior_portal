package com.example.junior_portal.dtos.dto;

import com.example.junior_portal.model.chat.MessageStatus;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class ChatMessageDto {

    private Long id;
    private Long chatId;
    private Long senderId;
    private Long recipientId;
    private String senderName;
    private String recipientName;
    private String content;
    private Date timestamp;
    private MessageStatus status;
}
