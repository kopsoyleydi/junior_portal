package com.example.junior_portal.data.repository;

import com.example.junior_portal.model.chat.ChatMessage;
import com.example.junior_portal.model.chat.MessageStatus;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Transactional
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public interface ChatMessageRepository extends JpaRepository<ChatMessage, Long> {
    int countBySenderIdAndRecipientIdAndStatus(
            Long senderId, Long recipientId, MessageStatus status);

    List<ChatMessage> findByChatId(Long chatId);

    List<ChatMessage> findBySenderIdAndRecipientId(Long senderId, Long recipientId);

    @Modifying
    @Query("UPDATE ChatMessage c SET c.status = :status WHERE c.senderId = :senderId AND c.recipientId = :recipientId")
    void updateStatusBySenderIdAndRecipientId(MessageStatus status,
                                              Long senderId,
                                              Long recipientId);

}
