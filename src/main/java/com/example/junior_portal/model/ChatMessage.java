package com.example.junior_portal.model;

import jakarta.persistence.*;
import lombok.Data;

import java.sql.Timestamp;

@Entity
@Table(name = "chat_messages")
@Data
public class ChatMessage {

    @Id
    @Column(name = "chat_message_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JoinColumn(name = "sender_id")
    @ManyToOne
    private User senderId;

    @JoinColumn(name = "receiver_id")
    @ManyToOne
    private User receiverId;

    @Column(name = "content")
    private String content;

    @Column(name = "send_time")
    private Timestamp timestamp;
}
