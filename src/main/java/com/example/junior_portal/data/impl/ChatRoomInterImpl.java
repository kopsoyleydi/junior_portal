package com.example.junior_portal.data.impl;

import com.example.junior_portal.data.impl.inter.ChatRoomRepoInter;
import com.example.junior_portal.data.repository.ChatRoomRepository;
import com.example.junior_portal.model.chat.ChatRoom;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;



@Component
@RequiredArgsConstructor
public class ChatRoomInterImpl implements ChatRoomRepoInter {

    private final ChatRoomRepository chatRoomRepository;

    @Override
    public ChatRoom getChatRoom(Long senderId, Long recipientId) {
        ChatRoom chatRoom = chatRoomRepository.findBySenderIdAndRecipientId(senderId, recipientId);

        Long chatId = chatRoomRepository.maxId();
        if(chatRoom == null){
            ChatRoom room = ChatRoom
                    .builder()
                    .chatId(chatId)
                    .senderId(senderId)
                    .recipientId(recipientId)
                    .build();

            chatRoomRepository.save(room);
        }
        return chatRoomRepository
                .findBySenderIdAndRecipientId(senderId, recipientId);
    }
}
