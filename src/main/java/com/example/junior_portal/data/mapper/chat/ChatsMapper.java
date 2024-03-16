package com.example.junior_portal.data.mapper.chat;

import com.example.junior_portal.dtos.bodies.response.ChatsDto;
import com.example.junior_portal.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class ChatsMapper {

    public ChatsDto toChatsDto(User user){
        ChatsDto chatsDto = new ChatsDto();
        chatsDto.setUsername(user.getUsername());
        chatsDto.setMemberId(user.getId());
        chatsDto.setUserPictureLink(user.getPictureLink());
        return chatsDto;
    }

    public List<ChatsDto> toChatsList(List<User> users) {
        return users.stream()
                .map(this::toChatsDto)
                .collect(Collectors.toList());
    }
}
