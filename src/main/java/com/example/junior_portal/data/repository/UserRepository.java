package com.example.junior_portal.data.repository;

import com.example.junior_portal.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findAllByEmail(String email);
}
