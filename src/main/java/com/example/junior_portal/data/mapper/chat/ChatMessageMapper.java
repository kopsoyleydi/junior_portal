package com.example.junior_portal.data.mapper.chat;

import com.example.junior_portal.dtos.dto.chat.ChatMessageDto;
import com.example.junior_portal.model.chat.ChatMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class ChatMessageMapper {

    public ChatMessageDto toDto(ChatMessage chatMessage){
        ChatMessageDto chatMessageDto = new ChatMessageDto();
        chatMessageDto.setId(chatMessage.getId());
        chatMessageDto.setContent(chatMessage.getContent());
        chatMessageDto.setSenderId(chatMessage.getSenderId());
        chatMessageDto.setSenderName(chatMessage.getSenderName());
        chatMessageDto.setStatus(chatMessage.getStatus());
        chatMessageDto.setTimestamp(Instant.now());
        return chatMessageDto;
    };

    public ChatMessage toModel(ChatMessageDto chatMessageDto){
        ChatMessage chatMessage = new ChatMessage();
        chatMessage.setId(chatMessageDto.getId());
        chatMessage.setContent(chatMessageDto.getContent());
        chatMessage.setSenderId(chatMessageDto.getSenderId());
        chatMessage.setSenderName(chatMessageDto.getSenderName());
        chatMessage.setStatus(chatMessageDto.getStatus());
        chatMessage.setTimestamp(Instant.now());
        return chatMessage;
    };

    public List<ChatMessageDto> toDtoList(List<ChatMessage> chatMessages) {
        return chatMessages.stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    public List<ChatMessage> toModelList(List<ChatMessageDto> chatMessageDtos) {
        return chatMessageDtos.stream()
                .map(this::toModel)
                .collect(Collectors.toList());
    }
}
