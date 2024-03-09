package com.example.junior_portal.dtos.bodies.request;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class MessageBody {

    private Long senderId;

    private Long recipientId;

    private String content;

    private Date timestamp;

}
