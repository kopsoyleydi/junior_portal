package com.example.junior_portal.data.repository;

import com.example.junior_portal.model.Internship;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
@Transactional
@Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
public interface InternshipRepository extends JpaRepository<Internship, Long> {
}
