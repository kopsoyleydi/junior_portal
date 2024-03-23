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

    @Query("select c from Message c where c.message_from.id = :userId" +
            " union all select t from Message t where t.message_to.id = :userId")
    List<Message> findMessagesByCurrentUserId(Long userId);

    @Query("select c from Message c where c.message_from.id = :userId and c.message_to.id = :message_to " +
            "union all select t from Message t where t.message_to.id = :userId and t.message_from.id = :message_to ")
    List<Message> findAllMessagesByChat(Long userId, Long message_to);

    @Query("select count(*) from Message c where c.message_from.id = :userId and c.message_to.id = :message_to and c.status = 1" +
            " or (c.message_to.id = :userId and c.message_from.id = :message_to and c.status = 1)")
    int countAllByStatus(Long userId, Long message_to);

    @Query("select c from Message c where c.message_from.id = :userId and c.message_to.id = :message_to order by " +
            "c.created_at desc limit 1")
    Message findMessageByChat(Long userId, Long message_to);
}
