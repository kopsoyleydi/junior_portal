package com.example.junior_portal.dtos.dto.chat;


import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
@Getter
@Setter
public class ConversationDto {

    private Long id;

    private String type;

    private LocalDateTime createdAt;
}
