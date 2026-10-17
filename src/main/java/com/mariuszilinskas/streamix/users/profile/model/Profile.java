package com.mariuszilinskas.streamix.users.profile.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

/**
 * This entity describes a user profile within the platform.
 * Profiles are automatically removed when the associated user account is deleted.
 *
 * @author Marius Zilinskas
 */
@Entity
@Getter
@Setter
@Table(name = "profiles", schema = "streamix_profile")
public class Profile {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
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

    @Column(name = "is_default", nullable = false)
    private boolean isDefault;

}
