package com.example.junior_portal.dtos.dto.chat;

import com.example.junior_portal.dtos.dto.UserDto;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserConversationDto {

    private Long id;

    @ManyToOne
    private UserDto user;

    @ManyToOne
    private ConversationDto conversation;
}
