package com.example.junior_portal.data.impl.inter;

import com.example.junior_portal.model.User;
import org.springframework.stereotype.Service;

import java.util.List;

public interface UserRepoInter {

    User createUser(User user);

    User findById(Long id);

    List<User> findAllUsers();

    User changeUser(User user);

    User getUserByEmail(String email);

    void updatePassword(String email, String password);
}
