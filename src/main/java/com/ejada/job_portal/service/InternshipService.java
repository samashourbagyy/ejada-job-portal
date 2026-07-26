package com.ejada.job_portal.service;

import com.ejada.job_portal.Mapper.InternshipMapper;
import com.ejada.job_portal.dto.request.InternshipRequestDto;
import com.ejada.job_portal.dto.response.InternshipResponseDto;
import com.ejada.job_portal.entity.Company;
import com.ejada.job_portal.entity.Internship;
import com.ejada.job_portal.exception.ResourceNotFoundException;
import com.ejada.job_portal.repository.CompanyRepository;
import com.ejada.job_portal.repository.InternshipRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class InternshipService {

    private final InternshipRepository internshipRepository;
    private final CompanyService companyService;
    private final OpportunitySkillService opportunitySkillService;
    private final InternshipMapper internshipMapper;

    @PreAuthorize("hasRole('COMPANY')")
    @Transactional
    public void createInternship(
            @Valid InternshipRequestDto request,
            Long userId) {

        Company company = companyService.getCompanyByUserId(userId);

        Internship internship = internshipMapper.toEntity(request);

        internship.setCompanyId(company.getCompanyId());

        internshipRepository.save(internship);

        opportunitySkillService.saveSkills(
                internship.getOpportunityId(),
                request.getSkills());
    }

    public List<InternshipResponseDto> getAllInternships() {

        return internshipRepository.findAll()
                .stream()
                .map(internship -> {

                    InternshipResponseDto response =
                            internshipMapper.toResponse(internship);

                    response.setCompanyName(
                            companyService.getCompanyName(
                                    internship.getCompanyId()));

                    return response;
                })
                .toList();
    }

    public InternshipResponseDto getInternshipById(Long id) {

        Internship internship = internshipRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Internship not found"));

        InternshipResponseDto response =
                internshipMapper.toResponse(internship);

        response.setCompanyName(
                companyService.getCompanyName(
                        internship.getCompanyId()));

        return response;
    }
}