package com.example.junior_portal.data.impl;

import com.example.junior_portal.data.impl.inter.ProfileRepoInter;
import com.example.junior_portal.data.repository.ProfileRepository;
import com.example.junior_portal.model.Profile;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProfileRepoInterImpl implements ProfileRepoInter {

    private final ProfileRepository profileRepository;
    @Override
    public Profile createProfile(Profile profile) {
        return profileRepository.save(profile);
    }

    @Override
    public Profile getProfileByEmail(String email) {
        return profileRepository.getProfileByEmail(email);
    }

    @Override
    public Profile changeProfile(Profile profile) {
        return profileRepository.save(profile);
    }
}
