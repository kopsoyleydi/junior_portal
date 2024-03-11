package com.example.junior_portal.data.impl.inter;

import com.example.junior_portal.model.chat.ChatRoom;

public interface ChatRoomRepoInter {
    public ChatRoom addChatRoom(Long user1, Long user2);
    public ChatRoom getChatRoom(Long chatId);

    public ChatRoom saveMessages(ChatRoom chatRoom);
}
