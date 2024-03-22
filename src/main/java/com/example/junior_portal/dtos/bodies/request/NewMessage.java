package com.example.junior_portal.dtos.bodies.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class NewMessage {
    private Long id;

    private Long message_from;

    private Long message_to;

    private String content;

    private String username;

    private String type;
}
