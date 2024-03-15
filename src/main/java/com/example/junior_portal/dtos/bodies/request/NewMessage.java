package com.example.junior_portal.dtos.bodies.request;

import com.example.junior_portal.dtos.dto.UserDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class NewMessage {
    private Long id;

    private Long message_from;

    private Long message_to;

    private String content;
}
