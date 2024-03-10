package com.example.junior_portal.data.impl;

import com.example.junior_portal.data.impl.inter.UserRepoInter;
import com.example.junior_portal.data.repository.UserRepository;
import com.example.junior_portal.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class UserRepoInterImpl implements UserRepoInter {

    private final UserRepository userRepository;
    @Override
    public User createUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public User findById(Long id) {
        return userRepository.findAllById(id);
    }

    @Override
    public List<User> findAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public List<User> findAllUsersWithoutCurrentUsers(Long current) {
        return userRepository.getAllByIdNot(current);
    }

    @Override
    public User changeUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public User getUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public void updatePassword(String email, String password) {
        userRepository.updateByEmail(email, password);
    }
}
