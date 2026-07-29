package com.ejada.job_portal.controller;

import com.ejada.job_portal.dto.response.ApplicationResponseDto;
import com.ejada.job_portal.security.CustomUserDetails;
import com.ejada.job_portal.service.ApplicationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/applications")
@RequiredArgsConstructor
public class ApplicationController {

    private final ApplicationService applicationService;

    @PostMapping("/apply/{opportunityId}")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<String> applyForOpportunity(
            @PathVariable Long opportunityId,
            @AuthenticationPrincipal CustomUserDetails currentUser) {

        applicationService.applyForOpportunity(
                opportunityId,
                currentUser.getUserId());

        return ResponseEntity.ok("Application submitted successfully.");
    }

    @GetMapping("/my")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<List<ApplicationResponseDto>> getMyApplications(
            @AuthenticationPrincipal CustomUserDetails currentUser) {

        return ResponseEntity.ok(
                applicationService.getMyApplications(
                        currentUser.getUserId()));
    }

    @GetMapping("/opportunity/{opportunityId}")
    @PreAuthorize("hasRole('COMPANY')")
    public ResponseEntity<List<ApplicationResponseDto>> getApplicationsForOpportunity(
            @PathVariable Long opportunityId,
            @AuthenticationPrincipal CustomUserDetails currentUser) {

        return ResponseEntity.ok(
                applicationService.getApplicationsForOpportunity(
                        opportunityId,
                        currentUser.getUserId()));
    }

    @PutMapping("/{applicationId}/status")
    @PreAuthorize("hasRole('COMPANY')")
    public ResponseEntity<String> updateApplicationStatus(
            @PathVariable Long applicationId,
            @RequestParam String status,
            @AuthenticationPrincipal CustomUserDetails currentUser) {

        applicationService.updateApplicationStatus(
                applicationId,
                status,
                currentUser.getUserId());

        return ResponseEntity.ok("Application status updated successfully.");
    }
}