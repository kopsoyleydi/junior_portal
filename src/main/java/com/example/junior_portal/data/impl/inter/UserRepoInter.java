package com.example.junior_portal.data.impl.inter;

import com.example.junior_portal.model.User;
import org.springframework.stereotype.Service;

@Service
public interface UserRepoInter {

    User createUser(User user);

    User changeUser(User user);

    User getUserByEmail(String email);
}
