package com.example.junior_portal.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.Instant;

@Entity
@Table(name = "news")
@Data
public class News {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;

    private String description;

    private String imageUrl;

    @ManyToOne
    private Profile author;

    private Instant createdTime;

}
