package com.example.junior_portal.model.chat;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "chat_rooms")
@Data
public class ChatRoom {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "chat_id")
    private Long chatId;
    @Column(name = "sender_id")
    private Long senderId;
    @Column(name = "recipient_id")
    private Long recipientId;

}
