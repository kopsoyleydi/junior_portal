package com.example.junior_portal.data.mapper.chat;

import com.example.junior_portal.dtos.dto.chat.ChatRoomDto;
import com.example.junior_portal.model.chat.ChatRoom;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class ChatRoomMapper {
    public ChatRoomDto toDto(ChatRoom chatRoom){
        return ChatRoomDto.builder()
                .id(chatRoom.getId())
                .recipientId(chatRoom.getRecipientId())
                .build();
    };

    public ChatRoom toModel(ChatRoomDto chatRoomDto){
        return ChatRoom.builder()
                .id(chatRoomDto.getId())
                .recipientId(chatRoomDto.getRecipientId())
                .senderId(chatRoomDto.getSenderId())
                .build();
    };

    public List<ChatRoomDto> toDtoList(List<ChatRoom> chatRooms) {
        return chatRooms.stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    public List<ChatRoom> toModelList(List<ChatRoomDto> chatRoomDtos) {
        return chatRoomDtos.stream()
                .map(this::toModel)
                .collect(Collectors.toList());
    }
}
