package com.ejada.job_portal.controller;

import com.ejada.job_portal.dto.request.UserProfileRequestDto;
import com.ejada.job_portal.dto.response.UserProfileResponseDto;
import com.ejada.job_portal.security.CustomUserDetails;
import com.ejada.job_portal.service.UserProfileService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user-profiles")
@RequiredArgsConstructor
public class UserProfileController {

    private final UserProfileService userProfileService;

    @PostMapping
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<String> createProfile(
            @Valid @RequestBody UserProfileRequestDto request,
            @AuthenticationPrincipal CustomUserDetails userDetails) {

        userProfileService.createProfile(
                request,
                userDetails.getUserId());

        return ResponseEntity.status(HttpStatus.CREATED)
                .body("Profile created successfully");
    }

    @PutMapping
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<String> updateProfile(
            @Valid @RequestBody UserProfileRequestDto request,
            @AuthenticationPrincipal CustomUserDetails userDetails) {

        userProfileService.updateProfile(
                request,
                userDetails.getUserId());

        return ResponseEntity.ok("Profile updated successfully");
    }

    @GetMapping("/me")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<UserProfileResponseDto> getMyProfile(
            @AuthenticationPrincipal CustomUserDetails userDetails) {

        return ResponseEntity.ok(
                userProfileService.getProfileByUserId(
                        userDetails.getUserId()));
    }

    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<UserProfileResponseDto>> getAllProfiles() {

        return ResponseEntity.ok(
                userProfileService.getAllProfiles());
    }

    @GetMapping("/{profileId}")
    public ResponseEntity<UserProfileResponseDto> getProfileById(
            @PathVariable Long profileId) {

        return ResponseEntity.ok(
                userProfileService.getProfileByProfileId(profileId));
    }


}