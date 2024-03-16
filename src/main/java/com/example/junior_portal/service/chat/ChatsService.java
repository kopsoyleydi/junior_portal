package com.example.junior_portal.service.chat;

import com.example.junior_portal.data.impl.inter.UserRepoInter;
import com.example.junior_portal.data.impl.inter.chat.MessageRepoInter;
import com.example.junior_portal.data.mapper.chat.ChatsMapper;
import com.example.junior_portal.dtos.bodies.response.ChatsDto;
import com.example.junior_portal.dtos.dto.UserDto;
import com.example.junior_portal.model.User;
import com.example.junior_portal.model.chat.Message;
import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class ChatsService {

    private final UserRepoInter userRepoInter;

    private final MessageRepoInter messageRepoInter;

    private final ChatsMapper chatsMapper;

    public ResponseEntity<?> loadAllChats(Long currentUserId){
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
        HashSet<User> users = new HashSet<>();
        for(Long ids : usersIds){
            users.add(userRepoInter.findById(ids));
        }
        List<User> userList = users.stream().toList();
        return ResponseEntity.ok(chatsMapper.toChatsList(userList));
    }

    public ResponseEntity<?> countNewMessages(Long userId, Long messageTo){
        try {
            return ResponseEntity.ok(messageRepoInter.countNewMessagesByAllChats(userId, messageTo));
        }
        catch (Exception e){
            return ResponseEntity.internalServerError().body("Something went wrong");
        }
    }

    public ResponseEntity<?> getAllMessagesFromChat(Long userId, Long messageTo){
        try {
            return ResponseEntity.ok(messageRepoInter.currentChatMessages(userId, messageTo));
        }
        catch (Exception e){
            return ResponseEntity.internalServerError().body("Something went wrong");
        }
    }
}
