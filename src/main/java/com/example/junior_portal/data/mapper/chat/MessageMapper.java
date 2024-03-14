package com.example.junior_portal.data.mapper.chat;

import com.example.junior_portal.data.mapper.UserMapper;
import com.example.junior_portal.dtos.dto.chat.ChatRoomDto;
import com.example.junior_portal.dtos.dto.chat.MessageDto;
import com.example.junior_portal.model.chat.ChatRoom;
import com.example.junior_portal.model.chat.Message;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class MessageMapper {

    private final UserMapper userMapper;
    public MessageDto toDto(Message message){
        MessageDto messageDto = new MessageDto();
        messageDto.setId(message.getId());
        messageDto.setMessage_to(userMapper.toDto(message.getMessage_to()));
        messageDto.setMessage_from(userMapper.toDto(message.getMessage_from()));
        messageDto.setContent(message.getContent());
        messageDto.setCreated_at(message.getCreated_at());
        return messageDto;
    };

    public Message toModel(MessageDto messageDto){
        Message message = new Message();
        message.setId(messageDto.getId());
        message.setMessage_to(userMapper.toModel(messageDto.getMessage_to()));
        message.setMessage_from(userMapper.toModel(messageDto.getMessage_from()));
        message.setContent(messageDto.getContent());
        message.setCreated_at(message.getCreated_at());
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
