package com.ejada.job_portal.service;

import com.ejada.job_portal.entity.Company;
import com.ejada.job_portal.entity.Opportunity;
import com.ejada.job_portal.exception.ResourceNotFoundException;
import com.ejada.job_portal.repository.OpportunityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OpportunityService {

    private final OpportunityRepository opportunityRepository;
    private final CompanyService companyService;

    public List<Opportunity> getAllOpportunities() {
        return opportunityRepository.findAll();
    }

    public Opportunity getOpportunity(Long opportunityId) {
        return opportunityRepository.findById(opportunityId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Opportunity not found"));
    }

    public Opportunity getAvailableOpportunity(Long opportunityId) {

        Opportunity opportunity = getOpportunity(opportunityId);

        if ("CLOSED".equalsIgnoreCase(opportunity.getStatus())) {
            throw new IllegalStateException(
                    "This opportunity is closed");
        }

        return opportunity;
    }

    public Opportunity save(Opportunity opportunity) {
        return opportunityRepository.save(opportunity);
    }

    public Opportunity update(Opportunity opportunity) {
        return opportunityRepository.save(opportunity);
    }

    public void delete(Long opportunityId) {
        opportunityRepository.deleteById(opportunityId);
    }

    public Opportunity getOwnedOpportunity(Long opportunityId, Long userId) {

        Company company = companyService.getCompanyByUserId(userId);

        Opportunity opportunity = getOpportunity(opportunityId);

        if (!opportunity.getCompanyId().equals(company.getCompanyId())) {
            throw new AccessDeniedException(
                    "You can only manage your own opportunities");
        }

        return opportunity;
    }

    public String getOpportunityTitle(Long opportunityId) {
        return getOpportunity(opportunityId).getTitle();
    }

    public String getCompanyName(Long opportunityId) {

        Opportunity opportunity = getOpportunity(opportunityId);

        return companyService
                .getCompanyById(opportunity.getCompanyId())
                .getCompanyName();
    }
}