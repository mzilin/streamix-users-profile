package com.mariuszilinskas.streamix.users.profile.service;

import com.mariuszilinskas.streamix.users.profile.dto.CreateUserProfileRequest;
import com.mariuszilinskas.streamix.users.profile.model.Profile;
import com.mariuszilinskas.streamix.users.profile.repository.ProfileRepository;
import com.mariuszilinskas.streamix.web.exception.EntityExistsException;
import com.mariuszilinskas.streamix.web.exception.EntityLimitExceededException;
import com.mariuszilinskas.streamix.web.exception.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

import static com.mariuszilinskas.streamix.users.profile.util.ProfileUtils.MAX_PROFILES_PER_USER;

/**
 * Service implementation for managing user profiles.
 * This service handles user creation, information updates, and deletion.
 *
 * @author Marius Zilinskas
 */
@Service
@RequiredArgsConstructor
public class ProfileServiceImpl implements ProfileService {

    private static final Logger logger = LoggerFactory.getLogger(ProfileServiceImpl.class);
    private final ProfileRepository profileRepository;
    private final AvatarService avatarService;

    @Override
    @Transactional
    public Profile createUserProfile(UUID userId, CreateUserProfileRequest request) {
        logger.info("Creating User Profile for User [userId: '{}']", userId);
        checkNumberOfUserProfiles(userId);
        checkNameExists(userId, request.profileName());
        return populateNewUserProfileWithRequestData(userId, request);
    }

    private void checkNumberOfUserProfiles(UUID userId) {
        if (profileRepository.findAllByUserId(userId).size() > MAX_PROFILES_PER_USER) {
            throw new EntityLimitExceededException("profiles", MAX_PROFILES_PER_USER);
        }
    }

    private void checkNameExists(UUID userId, String profileName) {
        if (profileRepository.existsByUserIdAndProfileName(userId, profileName)) {
            throw new EntityExistsException(Profile.class, "name", profileName);
        }
    }

    private Profile populateNewUserProfileWithRequestData(UUID userId, CreateUserProfileRequest request) {
        Profile newProfile = new Profile(userId);
        return applyUserProfileUpdate(newProfile, request);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Profile> getAllUserProfiles(UUID userId) {
        logger.info("Getting All User Profiles for User [userId: '{}']", userId);
        return profileRepository.findAllByUserId(userId);
    }

    @Override
    @Transactional(readOnly = true)
    public Profile getUserProfile(UUID userId, UUID profileId) {
        logger.info("Getting User Profile for User [userId: '{}']", userId);
        return findUserProfileByIdAndUserId(profileId, userId);
    }

    @Override
    @Transactional
    public Profile updateUserProfile(UUID userId, UUID profileId, CreateUserProfileRequest request) {
        logger.info("Updating User Profile for User [userId: '{}']", userId);
        Profile profile = findUserProfileByIdAndUserId(profileId, userId);
        checkNameExists(userId, request.profileName(), profileId);
        return applyUserProfileUpdate(profile, request);
    }

    private void checkNameExists(UUID userId, String profileName, UUID profileId) {
        if (profileRepository.existsByUserIdAndProfileNameAndIdNot(userId, profileName, profileId)) {
            throw new EntityExistsException(Profile.class, "name", profileName);
        }
    }

    private Profile applyUserProfileUpdate(Profile profile, CreateUserProfileRequest request) {
        profile.updateProfile(
                request.profileName(),
                avatarService.getAvatar(request.avatarId()),
                request.isKid()
        );
        return profileRepository.save(profile);
    }

    @Override
    @Transactional
    public void deleteUserProfile(UUID userId, UUID profileId) {
        logger.info("Deleting User Profile for User [userId: '{}']", userId);
        Profile profile = findUserProfileByIdAndUserId(profileId, userId);
        profileRepository.delete(profile);
    }

    private Profile findUserProfileByIdAndUserId(UUID profileId, UUID userId) {
        return profileRepository.findByIdAndUserId(profileId, userId)
                .orElseThrow(() -> new ResourceNotFoundException(Profile.class, "id", profileId));
    }

    @Override
    @Transactional
    public void deleteAllUserProfiles(UUID userId) {
        logger.info("Deleting All User Profiles for User [userId: '{}']", userId);
        profileRepository.deleteAllByUserId(userId);
    }

}
