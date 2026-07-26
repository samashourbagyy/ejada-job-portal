package com.ejada.job_portal.controller;

import com.ejada.job_portal.dto.request.InternshipRequestDto;
import com.ejada.job_portal.dto.response.InternshipResponseDto;
import com.ejada.job_portal.security.CustomUserDetails;
import com.ejada.job_portal.service.InternshipService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/internships")
@RequiredArgsConstructor
public class InternshipController {

    private final InternshipService internshipService;

    @PostMapping
    public ResponseEntity<String> createInternship(
            @Valid @RequestBody InternshipRequestDto request,
            @AuthenticationPrincipal CustomUserDetails userDetails) {

        internshipService.createInternship(request, userDetails.getUserId());

        return ResponseEntity.status(HttpStatus.CREATED)
                .body("Internship created successfully");
    }

    @GetMapping
    public ResponseEntity<List<InternshipResponseDto>> getAllInternships() {

        return ResponseEntity.ok(
                internshipService.getAllInternships());
    }

    @GetMapping("/{id}")
    public ResponseEntity<InternshipResponseDto> getInternshipById(
            @PathVariable Long id) {

        return ResponseEntity.ok(
                internshipService.getInternshipById(id));
    }
}