package com.example.junior_portal.data.impl.inter;

import com.example.junior_portal.model.Profile;

import java.util.List;

public interface ProfileRepoInter {

    Profile createProfile(Profile profile);

    Profile getProfileByEmail(String email);

    Profile changeProfile(Profile profile);

    List<Profile> getAllProfiles();
}
