package com.example.junior_portal.handler;

import com.example.junior_portal.dtos.bodies.request.NewMessage;
import com.example.junior_portal.model.chat.Message;
import com.example.junior_portal.service.chat.MessageService;
import com.example.junior_portal.session.UserSession;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.AbstractWebSocketHandler;

import java.io.IOException;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

@Slf4j
@Component
@RequiredArgsConstructor
public class ChatWebSocketHandler extends AbstractWebSocketHandler {

    private final Map<Long, UserSession> sessions = new ConcurrentHashMap<>();
    private final Map<String, Long> userNameToSessionIdMap = new ConcurrentHashMap<>();
    private final ObjectMapper objectMapper;
    private final MessageService messageService;

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        String userName = null;
        String query = Objects.requireNonNull(session.getUri()).getQuery();
        userName = MyWebSocketHandler.getString(userName, query);

        if (userName != null) {
            sessions.put(Long.valueOf(session.getId()), new UserSession(session, userName));
            userNameToSessionIdMap.put(userName, Long.valueOf(session.getId()));
        } else {
            session.sendMessage(new TextMessage("Error: User name is required"));
            session.close(new CloseStatus(4001, "User name is required"));
        }
    }

    @Override
    public void handleMessage(WebSocketSession session, WebSocketMessage<?> message) throws Exception {
        String payload = message.getPayload().toString();
        Message parsedMessage = parseMessage(payload);
        if (parsedMessage != null) {
            handleChatMessage(session, parsedMessage);
        }
    }

    private Message parseMessage(String payload) {
        try {
            return objectMapper.readValue(payload, Message.class);
        } catch (JsonProcessingException e) {
            log.info("Error on parsing JSON message: " + e.getMessage());
        }
        return null;
    }

    private void handleChatMessage(WebSocketSession session, Message message) {
        UserSession userId = sessions.get(session.getId());
        Long message_to = userId.getMessage_to();
        Long message_from = userId.getMessage_from();
        NewMessage newMessage = new NewMessage(null, message_from, message_to, message.getContent());
        messageService.processMessaging(newMessage);

        Long sessionId = userNameToSessionIdMap.get(message_to);
        if (sessionId != null) {
            UserSession userSession = sessions.get(sessionId);
            if (userSession != null) {
                try {
                    userSession.getSession().sendMessage(new TextMessage(message.getContent()));
                } catch (IOException e) {
                    System.err.println("Error on sending WebSocket message: " + e.getMessage());
                }
            }
        }
    }
}