package com.example.junior_portal.service;

import com.example.junior_portal.data.repository.ChatMessageRepository;
import org.mockito.Mock;
import org.springframework.test.web.servlet.MockMvc;


public class ChatServicesTest {

    @Mock
    private MockMvc mockMvc;

    @Mock private ChatMessageRepository chatMessageRepository;
}
