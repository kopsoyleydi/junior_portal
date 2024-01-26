package com.example.junior_portal.service.chat;

import com.example.junior_portal.data.impl.inter.ChatRoomRepoInter;
import com.example.junior_portal.dtos.bodies.request.CreateRoom;
import com.example.junior_portal.dtos.response.CommonResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
@Slf4j
public class ChatRoomService {

    private final ChatRoomRepoInter chatRoomRepoInter;

    public CommonResponse createRoom(CreateRoom createRoom){
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
            log.info("Service: ChatRoomService, create room method");
            return CommonResponse.builder()
                    .message("Something newt wrong").status(HttpStatus.valueOf(501))
                    .build();
        }
    }
}
