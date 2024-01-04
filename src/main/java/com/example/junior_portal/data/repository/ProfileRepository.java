package com.example.junior_portal.data.repository;

import com.example.junior_portal.model.Profile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProfileRepository extends JpaRepository<Profile, Long> {
}
