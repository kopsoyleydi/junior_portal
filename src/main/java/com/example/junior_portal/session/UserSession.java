package com.example.junior_portal.session;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.socket.WebSocketSession;

@Getter
@Setter
@AllArgsConstructor
public class UserSession {

    private WebSocketSession session;
    private String username;
    private Long message_from;
    private Long message_to;

    public UserSession(WebSocketSession session, String userName) {
    }
}