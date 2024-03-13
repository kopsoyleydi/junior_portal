package com.example.junior_portal.service.chat;

import com.example.junior_portal.data.impl.inter.UserRepoInter;
import com.example.junior_portal.data.impl.inter.chat.MessageRepoInter;
import com.example.junior_portal.data.mapper.chat.ChatsMapper;
import com.example.junior_portal.dtos.bodies.response.ChatsDto;
import com.example.junior_portal.dtos.dto.UserDto;
import com.example.junior_portal.model.User;
import com.example.junior_portal.model.chat.Message;
import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class ChatsService {

    private final UserRepoInter userRepoInter;

    private final MessageRepoInter messageRepoInter;

    private ChatsMapper chatsMapper;

    public List<ChatsDto> loadAllChats(Long currentUserId){
        List<Message> messages = messageRepoInter.loadChats(currentUserId);
        List<Long> usersIds = new ArrayList<>();
        for(Message m : messages){
            if(Objects.equals(m.getMessage_from().getId(), currentUserId)){
                usersIds.add(m.getMessage_to().getId());
            }
            if(Objects.equals(m.getMessage_to().getId(), currentUserId)){
                usersIds.add(m.getMessage_from().getId());
            }
        }
        List<User> users = new ArrayList<>();
        for(Long ids : usersIds){
            users.add(userRepoInter.findById(ids));
        }
        return chatsMapper.toChatsList(users);
    }
}