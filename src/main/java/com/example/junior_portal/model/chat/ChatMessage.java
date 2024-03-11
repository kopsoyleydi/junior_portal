package com.example.junior_portal.model.chat;

import jakarta.persistence.*;
import lombok.Data;

import java.time.Instant;

@Entity
@Table(name = "chat_messages")
@Data
public class ChatMessage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "sender_id")
    private Long senderId;
    @Column(name = "sender_id")
    private Long chatId;
    @Column(name = "sender_name")
    private String senderName;
    @Column(name = "content")
    private String content;
    @Column(name = "timestamp")
    private Instant timestamp;
    @Column(name = "status")
    private MessageStatus status;
}
