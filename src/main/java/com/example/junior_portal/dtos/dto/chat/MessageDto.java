package com.example.junior_portal.dtos.dto.chat;

import com.example.junior_portal.dtos.dto.UserDto;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class MessageDto {

    private Long id;

    private UserDto message_from;

    private UserDto message_to;

    private String content;

    private LocalDateTime created_at;
}
