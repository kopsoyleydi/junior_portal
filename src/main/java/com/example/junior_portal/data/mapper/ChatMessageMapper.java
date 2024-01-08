package com.example.junior_portal.data.mapper;

import com.example.junior_portal.dtos.dto.ChatMessageDto;
import com.example.junior_portal.dtos.dto.UserDto;
import com.example.junior_portal.model.ChatMessage;
import com.example.junior_portal.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;

import java.util.List;
import java.util.stream.Collectors;

@Configuration
@RequiredArgsConstructor
public class ChatMessageMapper {

    private final PermissionMapper permissionMapper;

    public ChatMessageDto toDto(ChatMessage chatMessage){
        ChatMessageDto chatMessageDto = new ChatMessageDto();
        chatMessageDto.setId(chatMessage.getId());
        chatMessageDto.setContent(chatMessage.getContent());
        UserDto userDto = new UserDto();
        userDto.setId(chatMessage.getSenderId().getId());
        userDto.setPassword(chatMessage.getSenderId().getPassword());
        userDto.setEmail(chatMessage.getSenderId().getEmail());
        userDto.setPermissions(permissionMapper.toDtoList((chatMessage.getSenderId().getPermissions())));
        userDto.setUsername(chatMessage.getSenderId().getUsername());
        chatMessageDto.setSenderId(userDto);
        UserDto userDto1 = new UserDto();
        userDto1.setId(chatMessage.getReceiverId().getId());
        userDto1.setPassword(chatMessage.getReceiverId().getPassword());
        userDto1.setEmail(chatMessage.getReceiverId().getEmail());
        userDto1.setPermissions(permissionMapper.toDtoList((chatMessage.getReceiverId().getPermissions())));
        userDto1.setUsername(chatMessage.getReceiverId().getUsername());
        chatMessageDto.setReceiverId(userDto1);
        chatMessageDto.setTimestamp(chatMessage.getTimestamp());
        return chatMessageDto;
    };

    public ChatMessage toModel(ChatMessageDto chatMessageDto){
        ChatMessage chatMessage = new ChatMessage();
        chatMessage.setId(chatMessageDto.getId());
        chatMessage.setContent(chatMessageDto.getContent());
        User user = new User();
        user.setId(chatMessageDto.getSenderId().getId());
        user.setEmail(chatMessageDto.getSenderId().getEmail());
        user.setUsername(chatMessageDto.getSenderId().getUsername());
        user.setPassword(chatMessageDto.getSenderId().getPassword());
        user.setPermissions(permissionMapper.toModelList(chatMessageDto.getSenderId().getPermissions()));
        chatMessage.setSenderId(user);
        User receiver = new User();
        receiver.setId(chatMessageDto.getReceiverId().getId());
        receiver.setEmail(chatMessageDto.getReceiverId().getEmail());
        receiver.setPassword(chatMessageDto.getReceiverId().getPassword());
        receiver.setUsername(chatMessageDto.getReceiverId().getUsername());
        receiver.setPermissions(permissionMapper.toModelList(chatMessageDto.getReceiverId().getPermissions()));
        chatMessage.setReceiverId(receiver);
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
