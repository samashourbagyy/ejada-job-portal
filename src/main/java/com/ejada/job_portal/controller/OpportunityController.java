package com.ejada.job_portal.controller;

import com.ejada.job_portal.entity.Opportunity;
import com.ejada.job_portal.service.OpportunityService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/opportunities")
@RequiredArgsConstructor
public class OpportunityController {

    private final OpportunityService opportunityService;

    @GetMapping
    public ResponseEntity<List<Opportunity>> getAllOpportunities() {

        return ResponseEntity.ok(
                opportunityService.getAllOpportunities());
    }

    @GetMapping("/company/{companyId}")
    public ResponseEntity<List<Opportunity>> getOpportunitiesByCompanyId(
            @PathVariable Long companyId) {

        return ResponseEntity.ok(
                opportunityService.getOpportunitiesByCompanyId(companyId));
    }

    @GetMapping("/company")
    public ResponseEntity<List<Opportunity>> getOpportunitiesByCompanyName(
            @RequestParam String companyName) {

        return ResponseEntity.ok(
                opportunityService.getOpportunitiesByCompanyName(companyName));
    }
}