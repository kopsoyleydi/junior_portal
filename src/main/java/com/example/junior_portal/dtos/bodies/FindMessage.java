package com.example.junior_portal.dtos.bodies;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FindMessage {
    private Long senderId;

    private Long recipientId;
}
