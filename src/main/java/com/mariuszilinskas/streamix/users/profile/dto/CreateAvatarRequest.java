package com.mariuszilinskas.streamix.users.profile.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.springframework.web.multipart.MultipartFile;

import static com.mariuszilinskas.streamix.web.constant.ValidationMessages.*;

public record CreateAvatarRequest(

        @NotBlank(message = "avatarName" + CANNOT_BE_BLANK)
        String avatarName,

        @NotNull(message = "file" + CANNOT_BE_NULL)
        MultipartFile file

){}
