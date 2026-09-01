package com.mariuszilinskas.streamix.users.profile.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import static com.mariuszilinskas.streamix.web.constant.ValidationMessages.*;

import java.util.UUID;

public record CreateUserProfileRequest(

        @NotBlank(message = "profileName" + CANNOT_BE_BLANK)
        String profileName,

        @NotNull(message = "avatarId" + CANNOT_BE_NULL)
        UUID avatarId,

        @NotNull(message = "isKid" + CANNOT_BE_NULL)
        boolean isKid

){}
