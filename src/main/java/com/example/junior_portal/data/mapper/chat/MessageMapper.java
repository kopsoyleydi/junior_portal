package com.example.junior_portal.data.mapper.chat;

import com.example.junior_portal.data.mapper.UserMapper;
import com.example.junior_portal.dtos.dto.chat.MessageDto;
import com.example.junior_portal.model.chat.Message;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class MessageMapper {

    private final UserMapper userMapper;

    private final ConversationMapper conversationMapper;

    public MessageDto toDto(Message message){
        MessageDto messageDto = new MessageDto();
        messageDto.setUser(userMapper.toDto(message.getUser()));
        messageDto.setConversation(conversationMapper.toDto(message.getConversation()));
        messageDto.setStatus(message.getStatus());
        messageDto.setContent(message.getContent());
        messageDto.setCreatedAt(message.getCreatedAt());
        messageDto.setId(message.getId());
        return messageDto;
    };

    public Message toModel(MessageDto messageDto){
        Message message = new Message();
        message.setUser(userMapper.toModel(messageDto.getUser()));
        message.setConversation(conversationMapper.toModel(messageDto.getConversation()));
        message.setCreatedAt(messageDto.getCreatedAt());
        message.setId(messageDto.getId());
        message.setStatus(messageDto.getStatus());
        message.setContent(messageDto.getContent());
        return message;
    };

    public List<MessageDto> toDtoList(List<Message> messages) {
        return messages.stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    public List<Message> toModelList(List<MessageDto> messageDtos) {
        return messageDtos.stream()
                .map(this::toModel)
                .collect(Collectors.toList());
    }
}
