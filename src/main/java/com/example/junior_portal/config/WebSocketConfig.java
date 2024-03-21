package com.example.junior_portal.config;

import com.example.junior_portal.handler.ChatWebSocketHandler;
import com.example.junior_portal.handler.MyTextWebSocketHandler;
import com.example.junior_portal.handler.MyWebSocketHandler;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.converter.DefaultContentTypeResolver;
import org.springframework.messaging.converter.MappingJackson2MessageConverter;
import org.springframework.messaging.converter.MessageConverter;
import org.springframework.messaging.core.MessageSendingOperations;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.util.MimeTypeUtils;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.config.annotation.*;
import org.springframework.web.socket.server.standard.ServletServerContainerFactoryBean;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Configuration
@EnableWebSocket
@RequiredArgsConstructor
public class WebSocketConfig implements WebSocketConfigurer, WebSocketMessageBrokerConfigurer {

    private final ChatWebSocketHandler chatWebSocketHandler;
    private final MyTextWebSocketHandler myTextWebSocketHandler;
    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {

        registry.addHandler(chatWebSocketHandler, "/app").setAllowedOrigins("http://127.0.0.1:5500")
                .withSockJS().setWebSocketEnabled(false);
        registry.addHandler(chatWebSocketHandler, "/websocket/info").setAllowedOrigins("http://127.0.0.1:5500")
                .withSockJS().setWebSocketEnabled(true).setStreamBytesLimit(512 * 1024)
                .setHttpMessageCacheSize(1000)
                .setDisconnectDelay(30 * 1000);
    }

    @Bean
    public ServletServerContainerFactoryBean createWebSocketContainer() {
        ServletServerContainerFactoryBean container = new ServletServerContainerFactoryBean();
        container.setMaxTextMessageBufferSize(8192);
        container.setMaxBinaryMessageBufferSize(8192);
        return container;
    }


    @Override
    public boolean configureMessageConverters(List<MessageConverter> messageConverters) {
        DefaultContentTypeResolver resolver = new DefaultContentTypeResolver();
        resolver.setDefaultMimeType(MimeTypeUtils.APPLICATION_JSON);
        MappingJackson2MessageConverter converter = new MappingJackson2MessageConverter();
        converter.setObjectMapper(new ObjectMapper());
        converter.setContentTypeResolver(resolver);
        messageConverters.add(converter);
        return false;
    }

}