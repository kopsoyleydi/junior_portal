package com.example.junior_portal.data.impl.chat;

import com.example.junior_portal.data.impl.inter.chat.MessageRepoInter;
import com.example.junior_portal.data.repository.MessageRepository;
import com.example.junior_portal.model.chat.Message;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class MessageRepoInterImpl implements MessageRepoInter {

    private final MessageRepository messageRepository;
    @Override
    public Message sendMessage(Message message) {
        return messageRepository.save(message);
    }

    @Override
    public List<Message> loadChats(Long userId) {
        return messageRepository.findMessagesByCurrentUserId(userId);
    }

    @Override
    public List<Message> currentChatMessages(Long userId, Long message_from) {
        return messageRepository.findAllMessagesByChat(userId, message_from);
    }

    @Override
    public int countNewMessagesByAllChats(Long userId, Long messageTo) {
        return messageRepository.countAllByStatus(userId, messageTo);
    }
}
