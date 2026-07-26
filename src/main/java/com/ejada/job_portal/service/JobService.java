package com.ejada.job_portal.service;
import com.ejada.job_portal.Mapper.JobMapper;
import com.ejada.job_portal.dto.request.JobRequestDto;
import com.ejada.job_portal.dto.response.JobResponseDto;
import com.ejada.job_portal.entity.Company;
import com.ejada.job_portal.entity.Job;
import com.ejada.job_portal.entity.OpportunitySkill;
import com.ejada.job_portal.exception.ResourceNotFoundException;
import com.ejada.job_portal.repository.CompanyRepository;
import com.ejada.job_portal.repository.JobRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class JobService {

    private final JobRepository jobRepository;
    private final CompanyService companyService;
    private final OpportunitySkillService opportunitySkillService;
    private final JobMapper jobMapper;

    @PreAuthorize("hasRole('COMPANY')")
    @Transactional
    public void createJob(
            @Valid JobRequestDto request,
            Long userId) {

        Company company = companyService.getCompanyByUserId(userId);

        Job job = jobMapper.toEntity(request);

        job.setCompanyId(company.getCompanyId());

        job = jobRepository.save(job);   // <-- Oracle generates OPPORTUNITY_ID

        opportunitySkillService.saveSkills(
                job.getOpportunityId(),
                request.getSkills());}

    public List<JobResponseDto> getAllJobs() {

        return jobRepository.findAll()
                .stream()
                .map(job -> {

                    JobResponseDto response = jobMapper.toResponse(job);

                    response.setCompanyName(
                            companyService.getCompanyName(job.getCompanyId()));

                    return response;
                })
                .toList();
    }

    public JobResponseDto getJobById(Long id) {

        Job job = jobRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Job not found"));

        JobResponseDto response = jobMapper.toResponse(job);

        response.setCompanyName(
                companyService.getCompanyName(job.getCompanyId()));

        return response;
    }
}