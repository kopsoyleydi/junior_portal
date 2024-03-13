package com.example.junior_portal.data.repository;

import com.example.junior_portal.model.chat.Message;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Transactional
public interface MessageRepository extends JpaRepository<Message, Long> {

    @Query("select c from Message c where c.message_from = :userId" +
            " union all select t from Message t where t.message_to = :userId")
    List<Message> findMessagesByCurrentUserId(Long userId);

    @Query("select c from Message c where c.message_from = :userId and c.message_to = :message_to " +
            "union all select t from Message t where t.message_to = :userId and t.message_from = :message_to ")
    List<Message> findAllMessagesByChat(Long userId, Long message_to);
}
