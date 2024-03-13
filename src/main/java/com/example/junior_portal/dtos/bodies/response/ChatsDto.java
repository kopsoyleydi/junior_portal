package com.example.junior_portal.dtos.bodies.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ChatsDto {

    private Long memberId;

    private String username;

    private String userPictureLink;
}
