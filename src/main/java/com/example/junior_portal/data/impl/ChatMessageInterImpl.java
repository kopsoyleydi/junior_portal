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
    public int countNewMessages(Long senderId, Long recipientId) {
        return chatMessageRepository
                .countBySenderIdAndRecipientId(senderId, recipientId);
    }

    @Override
    public List<ChatMessage> findChatMessages(Long senderId, Long recipientId) {
         ChatRoom chat = chatRoomInter.getChatRoom(senderId, recipientId);
         Long chatId = chat.getChatId();
        List<ChatMessage> messages =
                chatMessageRepository.findByChatId(chatId);

        if(!messages.isEmpty()) {
            updateStatuses(senderId, recipientId, MessageStatus.DELIVERED);
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
