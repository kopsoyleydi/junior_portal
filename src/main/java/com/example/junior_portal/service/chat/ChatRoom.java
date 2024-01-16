package com.example.junior_portal.service.chat;

import com.example.junior_portal.data.impl.inter.ChatRoomRepoInter;
import com.example.junior_portal.dtos.bodies.CreateRoom;
import com.example.junior_portal.dtos.response.CommonResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ChatRoom {

    private final ChatRoomRepoInter chatRoomRepoInter;

    private CommonResponse createRoom(CreateRoom createRoom){
        try {
            if(createRoom.getMessage() == null) {
                return CommonResponse.builder()
                        .message("User should send message").status(HttpStatus.valueOf(406))
                        .build();
            }
            return CommonResponse.builder()
                    .answer(chatRoomRepoInter.getChatRoom(createRoom.getSenderId(), createRoom.getRecipientId()))
                    .message("Room was successfully created").status(HttpStatus.OK)
                    .build();
        }
        catch (Exception e){
            e.getStackTrace();
            return CommonResponse.builder()
                    .message("Something newt wrong").status(HttpStatus.valueOf(501))
                    .build();
        }
    }
}
