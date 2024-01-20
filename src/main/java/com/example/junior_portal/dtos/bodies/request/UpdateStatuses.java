package com.example.junior_portal.dtos.bodies.request;

import com.example.junior_portal.model.chat.MessageStatus;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateStatuses {

    private Long senderId;
    private Long recipientId;
    private MessageStatus messageStatus;
}
