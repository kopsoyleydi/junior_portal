package com.example.junior_portal.data.mapper.chat;

import com.example.junior_portal.dtos.dto.chat.ConversationDto;
import com.example.junior_portal.model.chat.Conversation;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;


import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class ConversationMapper {

    public ConversationDto toDto(Conversation conversation){
        ConversationDto conversationDto = new ConversationDto();
        conversationDto.setCreatedAt(conversation.getCreatedAt());
        conversationDto.setType(conversation.getType());
        conversationDto.setId(conversation.getId());
        return conversationDto;
    };

    public Conversation toModel(ConversationDto conversationDto){
        Conversation conversation = new Conversation();
        conversation.setCreatedAt(conversationDto.getCreatedAt());
        conversation.setType(conversationDto.getType());
        conversation.setId(conversationDto.getId());
        return conversation;
    };

    public List<ConversationDto> toDtoList(List<Conversation> conversations) {
        return conversations.stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    public List<Conversation> toModelList(List<ConversationDto> conversationDtos) {
        return conversationDtos.stream()
                .map(this::toModel)
                .collect(Collectors.toList());
    }
}
