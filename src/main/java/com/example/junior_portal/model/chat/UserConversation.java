package com.example.junior_portal.model.chat;

import com.example.junior_portal.model.User;
import jakarta.persistence.*;

@Entity
@Table(name = "user_conversations")
public class UserConversation {
    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "conversation_id", nullable = false)
    private Conversation conversation;

    // Геттеры и сеттеры
}