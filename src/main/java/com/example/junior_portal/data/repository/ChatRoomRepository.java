package com.example.junior_portal.data.repository;

import com.example.junior_portal.model.chat.ChatRoom;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


@Repository
@Transactional
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public interface ChatRoomRepository extends JpaRepository<ChatRoom, Long> {
    ChatRoom findBySenderIdAndRecipientId(Long senderId, Long recipientId);

    @Query(value = "select max(s.id) from ChatRoom s")
    Long maxId();
}
