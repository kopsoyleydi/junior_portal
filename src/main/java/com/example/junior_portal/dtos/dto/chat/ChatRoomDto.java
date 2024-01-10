package com.example.junior_portal.dtos.dto.chat;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class ChatRoomDto {
    private Long id;
    private Long chatId;
    private Long senderId;
    private Long recipientId;
}
