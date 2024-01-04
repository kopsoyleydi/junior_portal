package com.example.junior_portal.dtos.dto;

import com.example.junior_portal.model.User;
import com.example.junior_portal.util.LocalDateTimeConverter;
import jakarta.persistence.Convert;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;

@Getter
@Setter
public class ChatMessageDto {

    private Long id;

    @ManyToOne
    private UserDto senderId;

    @ManyToOne
    private UserDto receiverId;

    private String content;

    @Convert(converter = LocalDateTimeConverter.class)
    private Timestamp timestamp;
}
