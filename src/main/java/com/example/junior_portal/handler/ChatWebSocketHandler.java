package com.example.junior_portal.handler;

import com.example.junior_portal.dtos.bodies.request.NewMessage;
import com.example.junior_portal.dtos.dto.chat.MessageDto;
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

    private final Map<String, UserSession> sessions = new ConcurrentHashMap<>();
    private final Map<String, String> usernameToSessionIdMap = new ConcurrentHashMap<>();
    private final ObjectMapper objectMapper;
    private final MessageService chatMessageService;

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        String userName = null;
        String query = Objects.requireNonNull(session.getUri()).getQuery();
        userName = getString(userName, query);

        if (userName != null) {
            sessions.put(session.getId(), new UserSession(session, userName));
            usernameToSessionIdMap.put(userName, session.getId());
        }
    }

    static String getString(String userName, String query) {
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
        return userName;
    }

    @Override
    public void handleMessage(WebSocketSession session, WebSocketMessage<?> message) throws Exception {
        String payload = message.getPayload().toString();
        MessageDto chatCustomMessage = parseMessage(payload);

        if (chatCustomMessage != null) {

            handleChatMessage(session, chatCustomMessage);

        }
    }

    private void handleChatMessage(WebSocketSession session, MessageDto message) {

        String sender = sessions.get(session.getId()).getUsername();
        String receiver = message.getMessage_to().getId().toString();

        NewMessage newMessage = new NewMessage();
        newMessage.setMessage_from(sessions.get(session.getId()).getMessage_from());
        newMessage.setMessage_to(message.getMessage_to().getId());
        newMessage.setContent(message.getContent());

        chatMessageService.processMessaging(newMessage);

        String sessionId = usernameToSessionIdMap.get(receiver);
        if (sessionId != null) {
            UserSession userSession = sessions.get(sessionId);
            if(userSession!=null){
                try{
                    userSession.getSession().sendMessage(new TextMessage(sender + " : " + message.getContent()));
                }catch (IOException e){
                    System.err.println("Error on sending WebSocket message " + e.getMessage());
                }
            }
        }

    }

    private MessageDto parseMessage(String payload) {
        try {
            return objectMapper.readValue(payload, MessageDto.class);
        } catch (JsonProcessingException e) {
            log.info("Error on parsing message " + e.getMessage());
            return null;
        }
    }
}