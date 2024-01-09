package com.example.junior_portal.data.repository;

import com.example.junior_portal.model.Profile;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
@Transactional
@Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)

public interface ProfileRepository extends JpaRepository<Profile, Long> {

    @Query(value = "SELECT p from Profile p where p.userId.email = :email")
    Profile getProfileByEmail(String email);
}
