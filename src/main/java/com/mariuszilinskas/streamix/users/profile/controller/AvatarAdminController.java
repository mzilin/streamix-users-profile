package com.mariuszilinskas.streamix.users.profile.controller;

import com.mariuszilinskas.streamix.users.profile.dto.CreateAvatarRequest;
import com.mariuszilinskas.streamix.users.profile.model.Avatar;
import com.mariuszilinskas.streamix.users.profile.service.AvatarService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.UUID;

/**
 * This class provides REST APIs for handling CRUD operations related to avatar management,
 * accessible to admins only.
 *
 * @author Marius Zilinskas
 */
@RestController
@RequestMapping("/admin/avatars")
@RequiredArgsConstructor
public class AvatarAdminController {

    private final AvatarService avatarService;

    @PostMapping
    public ResponseEntity<Avatar> createAvatar(
            @RequestPart("avatarName") String avatarName,
            @RequestPart("file") MultipartFile file
    ) throws IOException {
        CreateAvatarRequest request = new CreateAvatarRequest(avatarName, file);
        Avatar response =  avatarService.createAvatar(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @DeleteMapping("/{avatarId}")
    public ResponseEntity<Void> deleteAvatar(@PathVariable UUID avatarId) {
        avatarService.deleteAvatar(avatarId);
        ResponseEntity.noContent().build();
    }

}
