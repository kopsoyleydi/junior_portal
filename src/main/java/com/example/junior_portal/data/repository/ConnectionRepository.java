package com.example.junior_portal.data.repository;

import com.example.junior_portal.model.Connection;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ConnectionRepository extends JpaRepository<Connection, Long> {
}
