package com.example.junior_portal.service;


import com.example.junior_portal.data.impl.inter.ProfileRepoInter;
import com.example.junior_portal.data.mapper.ProfileMapper;
import com.example.junior_portal.model.Profile;
import com.example.junior_portal.model.User;
import com.example.junior_portal.service.portal.ProfileService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.mockito.InjectMocks;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.MOCK
)
@AutoConfigureMockMvc
public class ProfileServiceTest {

    @Autowired
    private MockMvc mockMvc;

    @InjectMocks
    private ProfileService profileService;

    @Autowired private ProfileRepoInter profileRepoInter;

    @Autowired private ProfileMapper profileMapper;
    @Autowired private PasswordEncoder passwordEncoder;

    private Profile profile;


    @BeforeEach
    void setUp(){
        Profile profile = new Profile();
        profile.setName("test");
        profile.setBio("Test my life");
        profile.setUniversity("IITU");
        profile.setExperience("3 years experience");
        User user = new User();
        user.setEmail("test@mail.ru");
        user.setId(1L);
        user.setUsername("test");
        user.setPassword(passwordEncoder.encode("test"));
    }

    @Test
    public void testFindByEmail(){
        when(profileRepoInter.getProfileByEmail("test@mail.ru")).thenReturn(profile);

        Profile found = profileRepoInter.getProfileByEmail("test@mail.ru");
        assertEquals("test", found.getName());
        assertEquals("3 years experience", found.getExperience());
    }
}
