package com.example.junior_portal.data.mapper;

import com.example.junior_portal.dtos.dto.ChatMessageDto;
import com.example.junior_portal.model.ChatMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;

import java.util.List;
import java.util.stream.Collectors;

@Configuration
@RequiredArgsConstructor
public class ChatMessageMapper {

    private final UserMapper userMapper;

    public ChatMessageDto toDto(ChatMessage chatMessage){
        ChatMessageDto chatMessageDto = new ChatMessageDto();
        chatMessageDto.setId(chatMessage.getId());
        chatMessageDto.setContent(chatMessage.getContent());
        chatMessageDto.setSenderId(userMapper.toDto(chatMessage.getSenderId()));
        chatMessageDto.setReceiverId(userMapper.toDto(chatMessage.getReceiverId()));
        chatMessageDto.setTimestamp(chatMessage.getTimestamp());
        return chatMessageDto;
    };

    public ChatMessage toModel(ChatMessageDto chatMessageDto){
        ChatMessage chatMessage = new ChatMessage();
        chatMessage.setId(chatMessageDto.getId());
        chatMessage.setContent(chatMessageDto.getContent());
        chatMessage.setSenderId(userMapper.toModel(chatMessageDto.getSenderId()));
        chatMessage.setReceiverId(userMapper.toModel(chatMessageDto.getReceiverId()));
        chatMessage.setTimestamp(chatMessageDto.getTimestamp());
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
