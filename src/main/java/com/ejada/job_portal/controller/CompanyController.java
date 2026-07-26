package com.ejada.job_portal.controller;

import com.ejada.job_portal.dto.request.CompanyRequestDto;
import com.ejada.job_portal.dto.response.CompanyResponseDto;
import com.ejada.job_portal.service.CompanyService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.BadRequestException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/companies")
@RequiredArgsConstructor
public class CompanyController {

    private final CompanyService companyService;

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<String> createCompany(
            @Valid @RequestBody CompanyRequestDto request) throws BadRequestException {

        companyService.createCompany(request);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body("Company created successfully");
    }

    @GetMapping
    public ResponseEntity<List<CompanyResponseDto>> getAllCompanies() {

        return ResponseEntity.ok(companyService.getAllCompanies());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CompanyResponseDto> getCompanyById(
            @PathVariable Long id) {

        return ResponseEntity.ok(companyService.getCompanyById(id));
    }
}