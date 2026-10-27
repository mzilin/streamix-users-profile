package com.mariuszilinskas.streamix.users.profile.service;

import com.mariuszilinskas.streamix.users.profile.dto.CreateUserProfileRequest;
import com.mariuszilinskas.streamix.users.profile.model.Profile;

import java.util.List;
import java.util.UUID;

public interface ProfileService {

    Profile createUserProfile(UUID userId, CreateUserProfileRequest request);

    List<Profile> getAllUserProfiles(UUID userId);

    Profile getUserProfile(UUID userId, UUID profileId);

    Profile updateUserProfile(UUID userId, UUID profileId, CreateUserProfileRequest request);

    void deleteUserProfile(UUID userId, UUID profileId);

    void deleteAllUserProfiles(UUID userId);

}
