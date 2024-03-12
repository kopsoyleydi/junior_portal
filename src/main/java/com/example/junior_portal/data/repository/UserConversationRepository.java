package com.example.junior_portal.data.repository;

import com.example.junior_portal.model.chat.UserConversation;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
@Transactional
public interface UserConversationRepository extends JpaRepository<UserConversation, Long> {
}
