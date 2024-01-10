package com.example.junior_portal.model.chat;

import jakarta.persistence.*;

@Entity
@Table(name = "chats")
public class Chat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
}
