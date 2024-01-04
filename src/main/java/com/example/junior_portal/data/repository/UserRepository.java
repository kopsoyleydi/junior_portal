package com.example.junior_portal.data.repository;

import com.example.junior_portal.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
