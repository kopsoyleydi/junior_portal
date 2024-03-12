package com.example.junior_portal.dtos.dto.chat;

import com.example.junior_portal.dtos.dto.UserDto;
import com.example.junior_portal.model.chat.MessageStatus;

import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class MessageDto {

    private Long id;

    private String content;

    private LocalDateTime createdAt;

    @ManyToOne
    private ConversationDto conversation;

    @ManyToOne
    private UserDto user;

    private MessageStatus status;
}
