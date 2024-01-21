package com.example.junior_portal.data.impl;

import com.example.junior_portal.data.impl.inter.UserRepoInter;
import com.example.junior_portal.data.repository.UserRepository;
import com.example.junior_portal.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserRepoInterImpl implements UserRepoInter {

    private final UserRepository userRepository;
    @Override
    public User createUser(User user) {
        return userRepository.save(user);
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
    public User updatePassword(String email, String password) {
        return userRepository.updateByEmail(email, password);
    }
}
