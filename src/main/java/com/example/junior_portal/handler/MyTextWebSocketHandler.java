package com.example.junior_portal.handler;

import com.example.junior_portal.dtos.bodies.request.NewMessage;
import com.example.junior_portal.model.chat.Message;
import com.example.junior_portal.service.chat.MessageService;
import com.google.gson.Gson;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.util.HashMap;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class MyTextWebSocketHandler extends TextWebSocketHandler {

    private final MessageService messageService;

    private final Map<String, WebSocketSession> sessions = new HashMap<>();

    @Bean("sessions")
    public Map<String, WebSocketSession> getSessions() {
        return sessions;
    }

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        Gson gson = new Gson();
        NewMessage newMessage = gson.fromJson(message.getPayload(), NewMessage.class);
        System.out.println(newMessage);
        messageService.processMessaging(newMessage);
        session.sendMessage(new TextMessage("Hello, " + message.getPayload() + "!"));
    }

}
