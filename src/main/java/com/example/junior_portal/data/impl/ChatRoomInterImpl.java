package com.example.junior_portal.data.impl;

import com.example.junior_portal.data.impl.inter.ChatRoomRepoInter;
import com.example.junior_portal.data.repository.ChatRepository;
import com.example.junior_portal.data.repository.ChatRoomRepository;
import com.example.junior_portal.model.chat.ChatRoom;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;



@Service
@RequiredArgsConstructor
public class ChatRoomInterImpl implements ChatRoomRepoInter {

    private final ChatRoomRepository chatRoomRepository;

    private final ChatRepository chatRepository;

    @Override
    public ChatRoom getChatRoom(
            Long senderId, Long recipientId) {
        ChatRoom chatRoom = chatRoomRepository.findBySenderIdAndRecipientId(senderId, recipientId);

        Long chatId = chatRepository.maxId();
        if(chatRoom == null){
            ChatRoom senderRecipient = ChatRoom
                    .builder()
                    .chatId(chatId)
                    .senderId(senderId)
                    .recipientId(recipientId)
                    .build();

            ChatRoom recipientSender = ChatRoom
                    .builder()
                    .chatId(chatId)
                    .senderId(recipientId)
                    .recipientId(senderId)
                    .build();
            chatRoomRepository.save(senderRecipient);
            chatRoomRepository.save(recipientSender);
        }
        return chatRoomRepository
                .findBySenderIdAndRecipientId(senderId, recipientId);
    }
}
