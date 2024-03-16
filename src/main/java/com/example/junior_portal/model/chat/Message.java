package com.example.junior_portal.model.chat;

import com.example.junior_portal.model.User;
import jakarta.persistence.*;
import lombok.Data;

import java.time.Instant;
import java.time.LocalDateTime;

@Entity
@Table(name = "messages")
@Data
public class Message {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "message_from")
    private User message_from;

    @ManyToOne
    @JoinColumn(name = "message_to")
    private User message_to;

    @Column(name = "content")
    private String content;

    @Column(name = "status")
    private MessageStatus status;

    @Column(name = "created_at")
    private Instant created_at;


}
