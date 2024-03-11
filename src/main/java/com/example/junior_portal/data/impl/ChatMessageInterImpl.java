package com.example.junior_portal.data.impl;

import com.example.junior_portal.data.impl.inter.ChatMessageRepoInter;
import com.example.junior_portal.data.repository.ChatMessageRepository;
import com.example.junior_portal.exception.ResourceNotFoundException;
import com.example.junior_portal.model.chat.ChatMessage;
import com.example.junior_portal.model.chat.ChatRoom;
import com.example.junior_portal.model.chat.MessageStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;


import java.util.List;

@Component
@RequiredArgsConstructor
public class ChatMessageInterImpl implements ChatMessageRepoInter {

    private final ChatMessageRepository chatMessageRepository;

    private final ChatRoomInterImpl chatRoomInter;
    @Override
    public ChatMessage create(ChatMessage chatMessage) {
        chatMessage.setStatus(MessageStatus.RECEIVED);
        return chatMessageRepository.save(chatMessage);
    }

    @Override
    public int countNewMessages(Long chatId) {
        return chatMessageRepository
                .countByIdAndStatus(chatId, MessageStatus.RECEIVED);
    }

    @Override
    public List<ChatMessage> findChatMessages(Long chatId) {
         ChatRoom chat = chatRoomInter.getChatRoom(chatId);
        List<ChatMessage> messages =
                chatMessageRepository.findAllByChatId(chatId);

        if(!messages.isEmpty()) {
            updateStatuses(chat.getSenderId(), chat.getRecipientId(), MessageStatus.DELIVERED);
        }

        return messages;
    }

    @Override
    public ChatMessage findById(Long id) {
         return chatMessageRepository
                .findById(id)
                .map(chatMessage -> {
                    chatMessage.setStatus(MessageStatus.DELIVERED);
                    return create(chatMessage);
                })
                .orElseThrow(() ->
                        new ResourceNotFoundException("can't find message (" + id + ")"));
    }

    @Override
    public void updateStatuses(Long senderId, Long recipientId, MessageStatus status) {
        chatMessageRepository.updateStatusBySenderIdAndRecipientId(status, senderId, recipientId);
    }
}
