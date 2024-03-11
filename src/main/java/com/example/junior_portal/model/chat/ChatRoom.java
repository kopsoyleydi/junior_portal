package com.example.junior_portal.model.chat;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "chat_rooms")
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ChatRoom {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "sender_id")
    private Long senderId;
    @Column(name = "recipient_id")
    private Long recipientId;

    @OneToMany
    @Column(name = "messages")
    private List<ChatMessage> messages;
}
