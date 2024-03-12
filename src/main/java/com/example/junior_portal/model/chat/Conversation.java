package com.example.junior_portal.model.chat;

import jakarta.persistence.*;

import java.time.LocalDateTime;


@Entity
@Table(name = "conversations")
public class Conversation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "type", nullable = false, length = 10)
    private String type;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    // Геттеры и сеттеры
}
