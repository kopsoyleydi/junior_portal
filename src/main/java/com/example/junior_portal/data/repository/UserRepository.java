package com.example.junior_portal.data.repository;

import com.example.junior_portal.model.User;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Transactional
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public interface UserRepository extends JpaRepository<User, Long> {
    User findByEmail(String email);

    User findAllById(Long id);

    List<User> getAllByIdNot(Long id);

    @Query("update User u set u.password = :password where u.email = :email")
    User updateByEmail(String email, String password);
}
