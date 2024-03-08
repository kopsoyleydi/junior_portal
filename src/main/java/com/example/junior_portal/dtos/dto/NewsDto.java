package com.example.junior_portal.dtos.dto;

import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

@Getter
@Setter
public class NewsDto {

    private Long id;
    private String title;

    private String description;

    private String imageUrl;

    @ManyToOne
    private ProfileDto author;

    private Instant createdTime;
}
