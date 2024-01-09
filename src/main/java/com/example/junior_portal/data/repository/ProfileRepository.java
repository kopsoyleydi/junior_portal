package com.example.junior_portal.data.repository;

import com.example.junior_portal.model.Profile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ProfileRepository extends JpaRepository<Profile, Long> {

    @Query(value = "SELECT p from Profile p where p.userId.email = :email")
    Profile getProfileByEmail(String email);
}
