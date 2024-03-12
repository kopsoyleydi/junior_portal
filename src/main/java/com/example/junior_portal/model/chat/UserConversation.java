package com.example.junior_portal.model.chat;

import com.example.junior_portal.model.User;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "user_conversations")
@Data
public class UserConversation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "conversation_id", nullable = false)
    private Conversation conversation;
}