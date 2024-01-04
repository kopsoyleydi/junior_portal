package com.example.junior_portal.data.repository;

import com.example.junior_portal.model.Permission;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PermissionRepository extends JpaRepository<Permission, Long> {
}
