package com.example.junior_portal.data.impl.inter.chat;

import com.example.junior_portal.model.chat.Message;


import java.util.List;

public interface MessageRepoInter {

    Message sendMessage(Message message);

    List<Message> loadChats(Long userId);

    List<Message> currentChatMessages(Long userId, Long message_from);

    int countNewMessagesByAllChats(Long userId, Long messageTo);

    Message findChatMessageByIds(Long userId, Long messageTo);
}
