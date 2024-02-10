package com.example.junior_portal.service;


import com.example.junior_portal.data.impl.inter.ProfileRepoInter;
import com.example.junior_portal.data.mapper.ProfileMapper;
import com.example.junior_portal.model.Profile;
import com.example.junior_portal.model.User;
import com.example.junior_portal.service.portal.ProfileService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.mockito.InjectMocks;

import org.mockito.Mock;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;


public class ProfileServiceTest {


    @InjectMocks
    private ProfileService profileService;

    @Mock private ProfileRepoInter profileRepoInter;

    @Mock private ProfileMapper profileMapper;
    @Mock private PasswordEncoder passwordEncoder;

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
