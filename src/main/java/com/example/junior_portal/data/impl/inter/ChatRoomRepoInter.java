package com.example.junior_portal.data.impl.inter;

import com.example.junior_portal.model.chat.ChatRoom;

public interface ChatRoomRepoInter {
    public ChatRoom getChatRoom(
            Long senderId, Long recipientId);
}
