package com.example.junior_portal.data.impl.inter;

import com.example.junior_portal.model.chat.ChatMessage;
import com.example.junior_portal.model.chat.MessageStatus;

import java.util.List;

public interface ChatMessageRepoInter {

    ChatMessage create(ChatMessage chatMessage);

    int countNewMessages(Long chatId);

    List<ChatMessage> findChatMessages(Long chatId);

    ChatMessage findById(Long id);

    void updateStatuses(Long senderId, Long recipientId, MessageStatus status);

 }
