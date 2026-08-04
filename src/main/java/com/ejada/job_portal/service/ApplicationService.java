package com.ejada.job_portal.service;

import com.ejada.job_portal.Mapper.ApplicationMapper;
import com.ejada.job_portal.dto.response.ApplicationResponseDto;
import com.ejada.job_portal.dto.response.UserProfileResponseDto;
import com.ejada.job_portal.entity.Application;
import com.ejada.job_portal.entity.Opportunity;
import org.springframework.security.access.AccessDeniedException;
import com.ejada.job_portal.entity.UserProfile;
import com.ejada.job_portal.exception.DuplicateResourceException;
import com.ejada.job_portal.exception.ResourceNotFoundException;
import com.ejada.job_portal.repository.ApplicationRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ApplicationService {

    private static final String STATUS_PENDING = "PENDING";
    private static final String STATUS_ACCEPTED = "ACCEPTED";
    private static final String STATUS_REJECTED = "REJECTED";

    private final ApplicationRepository applicationRepository;
    private final ApplicationMapper applicationMapper;

    private final UserProfileService profileService;
    private final OpportunityService opportunityService;

    //private final NotificationService notificationService;

    @PreAuthorize("hasRole('USER')")
    @Transactional
    public void applyForOpportunity(Long opportunityId, Long userId) {

        UserProfileResponseDto profile = profileService.getProfileByUserId(userId);

        Opportunity opportunity =
                opportunityService.getAvailableOpportunity(opportunityId);

        if (applicationRepository.findByProfileIdAndOpportunityId(
                profile.getProfileId(),
                opportunityId).isPresent()) {

            throw new DuplicateResourceException(
                    "You have already applied to this opportunity");
        }
        opportunityService.validateOpportunityAvailability(opportunity);

        Application application = Application.builder()
                .profileId(profile.getProfileId())
                .opportunityId(opportunityId)
                .applicationDate(new Date())
                .status(STATUS_PENDING)
                .build();

        applicationRepository.save(application);
    }

    @PreAuthorize("hasRole('USER')")
    public List<ApplicationResponseDto> getMyApplications(Long userId) {

        UserProfileResponseDto profile = profileService.getProfileByUserId(userId);

        return applicationRepository
                .findByProfileId(profile.getProfileId())
                .stream()
                .map(applicationMapper::toResponse)
                .toList();
    }

    @PreAuthorize("hasRole('COMPANY')")
    public List<ApplicationResponseDto> getApplicationsForOpportunity(
            Long opportunityId,
            Long userId)  {

        opportunityService.getOwnedOpportunity(opportunityId, userId);

        return applicationRepository
                .findByOpportunityId(opportunityId)
                .stream()
                .map(applicationMapper::toResponse)
                .toList();
    }

    @PreAuthorize("hasRole('COMPANY')")
    @Transactional
    public void updateApplicationStatus(
            Long applicationId,
            String status,
            Long userId)  {

        String normalized =
                status == null ? "" : status.trim().toUpperCase();

        if (!normalized.equals(STATUS_ACCEPTED)
                && !normalized.equals(STATUS_REJECTED)) {

            throw new IllegalArgumentException(
                    "Status must be ACCEPTED or REJECTED");
        }

        Application application = getApplication(applicationId);

        opportunityService.getOwnedOpportunity(
                application.getOpportunityId(),
                userId);

        application.setStatus(normalized);

        applicationRepository.save(application);


    }

    private Application getApplication(Long applicationId) {
        return applicationRepository.findById(applicationId)
                .orElseThrow(()->  new ResourceNotFoundException("Not found application"));
    }

    public Integer countByOpportunityId(Long opportunityId) {
        return applicationRepository.countByOpportunityId(opportunityId);
    }
}