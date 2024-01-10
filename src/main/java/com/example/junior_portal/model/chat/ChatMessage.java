package com.example.junior_portal.model.chat;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Entity
@Table(name = "chat_messages")
@Data
public class ChatMessage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "chat_id")
    private Long chatId;
    @Column(name = "sender_id")
    private Long senderId;
    @Column(name = "recipient_id")
    private Long recipientId;
    @Column(name = "sender_name")
    private String senderName;
    @Column(name = "recipient_name")
    private String recipientName;
    @Column(name = "content")
    private String content;
    @Column(name = "timestamp")
    private Date timestamp;
    @Column(name = "status")
    private MessageStatus status;
}
