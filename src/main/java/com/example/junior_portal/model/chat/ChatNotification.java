package com.example.junior_portal.model.chat;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ChatNotification {
    private String id;
    private String senderId;
    private String senderName;
}
