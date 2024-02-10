package com.example.junior_portal.service;


import com.example.junior_portal.data.repository.UserRepository;
import org.mockito.Mock;
import org.springframework.test.web.servlet.MockMvc;

public class UserAuthTest {

    @Mock private MockMvc mockMvc;

    @Mock private UserRepository userRepository;
}
