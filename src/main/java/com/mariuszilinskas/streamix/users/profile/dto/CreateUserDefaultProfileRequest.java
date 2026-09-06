package com.mariuszilinskas.streamix.users.profile.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import static com.mariuszilinskas.streamix.web.constant.ValidationMessages.*;

import java.util.UUID;

public record CreateUserDefaultProfileRequest(

        @NotNull(message = "userId " + CANNOT_BE_NULL)
        UUID userId,

        @NotBlank(message = "firstName " + CANNOT_BE_BLANK)
        String firstName

){}
