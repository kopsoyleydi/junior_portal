package com.example.junior_portal.data.repository;

import com.example.junior_portal.model.chat.Chat;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
@Transactional
public interface ChatRepository extends JpaRepository<Chat, Long> {

    @Query(value = "select max(s.id) from Chat s")
    Long maxId();
}
