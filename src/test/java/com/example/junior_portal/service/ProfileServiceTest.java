package com.example.junior_portal.service;


import com.example.junior_portal.data.impl.inter.ProfileRepoInter;
import com.example.junior_portal.data.mapper.ProfileMapper;
import com.example.junior_portal.dtos.dto.ProfileDto;
import com.example.junior_portal.service.portal.ProfileService;
import org.junit.jupiter.api.Test;
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

    @Autowired private ProfileRepoInter profileRepoInter;

    @Autowired private ProfileMapper profileMapper;

    @Test
    public void fillProfileTestSuccess(){
        ProfileDto profileDto = new ProfileDto();
        profileDto.setExperience("3 years old");
        profileDto.setBio("My name is Beksultan");
        profileDto.setUniversity("IITU");
    }
}
