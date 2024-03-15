package com.example.junior_portal.dtos.dto.chat;

import com.example.junior_portal.dtos.dto.UserDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MessageDto {

    private Long id;

    private UserDto message_from;

    private UserDto message_to;

    private String content;

    private LocalDateTime created_at;
}
