package com.example.junior_portal.data.impl;

import com.example.junior_portal.data.impl.inter.ChatRoomRepoInter;
import com.example.junior_portal.data.repository.ChatRoomRepository;
import com.example.junior_portal.model.chat.ChatRoom;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;



@Component
@RequiredArgsConstructor
public class ChatRoomInterImpl implements ChatRoomRepoInter {

    private final ChatRoomRepository chatRoomRepository;

    @Override
    public ChatRoom addChatRoom(Long user1, Long user2) {
        ChatRoom chatRoom = chatRoomRepository.findBySenderIdAndRecipientId(user1, user2);
        ChatRoom chatRoom1 = chatRoomRepository.findBySenderIdAndRecipientId(user2, user1);

        if(chatRoom == null && chatRoom1 == null){
            ChatRoom room = ChatRoom
                    .builder()
                    .senderId(user1)
                    .recipientId(user2)
                    .build();

            chatRoomRepository.save(room);
        }
        return chatRoomRepository.findBySenderIdAndRecipientId(user1, user2);
    }

    public ChatRoom getChatRoom(Long chatId) {
        return chatRoomRepository.findAllById(chatId);
    }

    @Override
    public ChatRoom saveMessages(ChatRoom chatRoom) {
        return chatRoomRepository.save(chatRoom);
    }
}
