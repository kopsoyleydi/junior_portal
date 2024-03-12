package com.example.junior_portal.data.mapper.chat;

import com.example.junior_portal.data.mapper.UserMapper;
import com.example.junior_portal.dtos.dto.chat.MessageDto;
import com.example.junior_portal.dtos.dto.chat.UserConversationDto;
import com.example.junior_portal.model.chat.Message;
import com.example.junior_portal.model.chat.UserConversation;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class UserConversationMapper {

    private final UserMapper userMapper;

    private final ConversationMapper conversationMapper;

    public UserConversationDto toDto(UserConversation userConversation){
        UserConversationDto userConversationDto = new UserConversationDto();
        userConversationDto.setConversation(conversationMapper.toDto(userConversation.getConversation()));
        userConversationDto.setUser(userMapper.toDto(userConversation.getUser()));
        userConversationDto.setId(userConversation.getId());
        return userConversationDto;
    };

    public UserConversation toModel(UserConversationDto userConversationDto){
        UserConversation userConversation = new UserConversation();
        userConversation.setConversation(conversationMapper.toModel(userConversationDto.getConversation()));
        userConversation.setUser(userMapper.toModel(userConversationDto.getUser()));
        userConversation.setId(userConversationDto.getId());
        return userConversation;
    };

    public List<UserConversationDto> toDtoList(List<UserConversation> userConversations) {
        return userConversations.stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    public List<UserConversation> toModelList(List<UserConversationDto> userConversationDtos) {
        return userConversationDtos.stream()
                .map(this::toModel)
                .collect(Collectors.toList());
    }
}
