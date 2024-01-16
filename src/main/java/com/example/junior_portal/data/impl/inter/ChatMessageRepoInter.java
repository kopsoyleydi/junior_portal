package com.example.junior_portal.data.impl.inter;

import com.example.junior_portal.model.chat.ChatMessage;
import com.example.junior_portal.model.chat.MessageStatus;

import java.util.List;

public interface ChatMessageRepoInter {

    ChatMessage create(ChatMessage chatMessage);

    List<ChatMessage> countNewMessages(Long senderId, Long recipientId);

    List<ChatMessage> findChatMessages(Long senderId, Long recipientId);

    ChatMessage findById(Long id);

    void updateStatuses(Long senderId, Long recipientId, MessageStatus status);
}
