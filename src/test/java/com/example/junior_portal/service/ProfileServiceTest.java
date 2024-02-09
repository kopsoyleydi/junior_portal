package com.example.junior_portal.service;


import com.example.junior_portal.service.portal.ProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.MOCK
)
@AutoConfigureMockMvc
public class ProfileServiceTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ProfileService profileService;
}
