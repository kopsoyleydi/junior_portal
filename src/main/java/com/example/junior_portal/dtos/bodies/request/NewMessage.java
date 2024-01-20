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
    private Long senderId;
    
    private Long recipientId;
}
