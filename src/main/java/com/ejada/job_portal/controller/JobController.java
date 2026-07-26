package com.ejada.job_portal.controller;

import com.ejada.job_portal.dto.request.JobRequestDto;
import com.ejada.job_portal.dto.response.JobResponseDto;
import com.ejada.job_portal.security.CustomUserDetails;
import com.ejada.job_portal.service.JobService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/jobs")
@RequiredArgsConstructor
public class JobController {

    private final JobService jobService;

    @PostMapping
    @PreAuthorize("hasRole('COMPANY')")
    public ResponseEntity<String> createJob(
            @Valid @RequestBody JobRequestDto request,
            @AuthenticationPrincipal CustomUserDetails userDetails) {

        jobService.createJob(request, userDetails.getUserId());

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body("Job created successfully");
    }

    @GetMapping
    public ResponseEntity<List<JobResponseDto>> getAllJobs() {
        return ResponseEntity.ok(jobService.getAllJobs());
    }

    @GetMapping("/{id}")
    public ResponseEntity<JobResponseDto> getJobById(
            @PathVariable Long id) {

        return ResponseEntity.ok(jobService.getJobById(id));
    }
}