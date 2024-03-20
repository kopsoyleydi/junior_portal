package com.example.junior_portal.config;

import com.example.junior_portal.handler.ChatWebSocketHandler;
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
    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        registry.addHandler(myWebSocketHandler, "/websocket");
        registry.addHandler(chatWebSocketHandler, "/app");
    }
}