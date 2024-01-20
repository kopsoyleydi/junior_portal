package com.example.junior_portal.dtos.bodies.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateRoom {
    private Long senderId;

    private Long recipientId;

    private String message;
}
