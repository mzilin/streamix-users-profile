package com.mariuszilinskas.streamix.users.profile.model;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.UUID;

/**
 * This entity describes a user profile within the platform.
 * Profiles are automatically removed when the associated user account is deleted.
 *
 * @author Marius Zilinskas
 */
@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "profiles", schema = "streamix_profile")
public class Profile {

    @Id
    @Column(updatable = false, nullable = false)
    private UUID id;

    @Column(name = "user_id", nullable = false)
    private UUID userId;

    @Column(name = "profile_name", nullable = false)
    private String profileName;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "avatar_id")
    private Avatar avatar;

    @Column(name = "is_kid", nullable = false)
    private boolean isKid;

    public Profile(UUID userId) {
        this.id = UUID.randomUUID();
        this.userId = userId;
    }

    public void updateProfile(String profileName, Avatar avatar, boolean isKid) {
        this.profileName = profileName;
        this.avatar = avatar;
        this.isKid = isKid;
    }

}
