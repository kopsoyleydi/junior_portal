package com.example.junior_portal.config;

import com.example.junior_portal.handler.ChatWebSocketHandler;
import com.example.junior_portal.handler.MyTextWebSocketHandler;
import com.example.junior_portal.handler.MyWebSocketHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.*;


@Configuration
@EnableWebSocket
@RequiredArgsConstructor
public class WebSocketConfig implements WebSocketConfigurer {

    private final MyWebSocketHandler myWebSocketHandler;
    private final ChatWebSocketHandler chatWebSocketHandler;
    private final MyTextWebSocketHandler myTextWebSocketHandler;
    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {

        registry.addHandler(chatWebSocketHandler, "/app").setAllowedOrigins("http://127.0.0.1:5500")
                .withSockJS().setWebSocketEnabled(false);
        registry.addHandler(myTextWebSocketHandler, "/websocket/info").setAllowedOrigins("http://127.0.0.1:5500")
                .withSockJS().setWebSocketEnabled(true);
    }
}