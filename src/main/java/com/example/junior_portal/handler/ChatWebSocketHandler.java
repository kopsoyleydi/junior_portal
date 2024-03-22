package com.example.junior_portal.handler;

import com.example.junior_portal.data.impl.inter.UserRepoInter;
import com.example.junior_portal.dtos.bodies.request.NewMessage;
import com.example.junior_portal.model.User;
import com.example.junior_portal.service.chat.MessageService;
import com.example.junior_portal.session.UserSession;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.AbstractWebSocketHandler;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

@Slf4j
@Component
@RequiredArgsConstructor
public class ChatWebSocketHandler extends AbstractWebSocketHandler {

    private final Map<String, UserSession> sessions = new ConcurrentHashMap<>();
    private final Map<String, String> usernameToSessionIdMap = new ConcurrentHashMap<>();
    private final ObjectMapper objectMapper;
    private final MessageService messageService;
    private final UserRepoInter userRepoInter;

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        String userName = null;
        String query = Objects.requireNonNull(session.getUri()).getQuery();
        if (query != null) {
            String[] queryParams = query.split("&");
            for (String param : queryParams) {
                String[] keyValue = param.split("=");
                if (keyValue[0].equals("username") && keyValue.length > 1) {
                    userName = keyValue[1];
                    break;
                }
            }
        }

        if (userName != null) {
            sessions.put(session.getId(), new UserSession(session, userName));
            System.out.println("User : " + userName + " joined" + " with session id " + session.getId());
            usernameToSessionIdMap.put(userName, session.getId());
        }
    }

    @Override
    public void handleMessage(WebSocketSession session, WebSocketMessage<?> message) throws Exception {
        String payload = message.getPayload().toString();
        NewMessage newMessage = parseMessage(payload);
        System.out.println(newMessage.getContent());
        if (newMessage != null) {
            handleChatMessage(session, newMessage);
        }
    }

    private void handleChatMessage(WebSocketSession session, NewMessage newMessage) {

        String sender = sessions.get(session.getId()).getUsername();
        User receiver = userRepoInter.findById(newMessage.getMessage_to());
        String json = null;
        String sessionId = usernameToSessionIdMap.get(receiver.getLogin());
        if (sessionId != null) {
            UserSession userSession = sessions.get(sessionId);
            if(userSession!=null){
                try{
                    try {
                        json = objectMapper.writeValueAsString(messageService.processMessaging(newMessage));
                        System.out.println(json);
                    } catch (JsonProcessingException e) {
                        System.err.println("Error converting object to JSON: " + e.getMessage());
                    }
                    assert json != null;
                    userSession.getSession().sendMessage(new TextMessage(json));
                    System.out.println("Message sent to all" + newMessage.getContent() + " " + userSession.getUsername());
                }catch (IOException e){
                    System.err.println("Error on sending WebSocket message " + e.getMessage());
                }
            }
        }

    }

    private NewMessage parseMessage(String payload) {
        try {
            return objectMapper.readValue(payload, NewMessage.class);
        } catch (JsonProcessingException e) {
            log.info("Error on parsing message " + e.getMessage());
            return null;
        }
    }

    private void sendMessageToAll(String message) {
        for (UserSession userSession : sessions.values()) {
            try {
                userSession.getSession().sendMessage(new TextMessage(message));
                System.out.println("Message sent to all" + message + " " + userSession.getUsername());
            } catch (Exception e) {
                System.err.println("Error on sending Websocket Message: " + message);
            }
        }
    }
}