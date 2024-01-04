package com.example.junior_portal.data.mapper;

import com.example.junior_portal.dtos.dto.ChatMessageDto;
import com.example.junior_portal.model.ChatMessage;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ChatMessageMapper {
    ChatMessageDto toDto(ChatMessage chatMessage);

    ChatMessage toModel(ChatMessageDto chatMessageDto);

    List<ChatMessageDto> toDtoList(List<ChatMessage> list);

    List<ChatMessage> toModelList(List<ChatMessageDto> list);
}
