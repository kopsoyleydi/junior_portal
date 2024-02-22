package com.example.junior_portal.service.chat;

import com.example.junior_portal.data.impl.inter.ChatRoomRepoInter;
import com.example.junior_portal.dtos.bodies.request.CreateRoom;
import com.example.junior_portal.dtos.response.CommonResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
@Slf4j
public class ChatRoomService {

    private final ChatRoomRepoInter chatRoomRepoInter;

    public ResponseEntity<?> createRoom(CreateRoom createRoom){
        try {
            if(createRoom.getMessage() == null) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body("User should send message");
            }
            return ResponseEntity.status(HttpStatus.OK)
                    .body(chatRoomRepoInter.getChatRoom(createRoom.getSenderId(), createRoom.getRecipientId()));
        }
        catch (Exception e){
            log.info("Service: ChatRoomService, create room method");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Something went wrong");
        }
    }
}
