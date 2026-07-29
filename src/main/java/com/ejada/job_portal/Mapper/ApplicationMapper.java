package com.ejada.job_portal.Mapper;

import com.ejada.job_portal.dto.response.ApplicationResponseDto;
import com.ejada.job_portal.entity.Application;
import com.ejada.job_portal.service.OpportunityService;
import com.ejada.job_portal.service.UserProfileService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ApplicationMapper {

    private final UserProfileService profileService;
    private final OpportunityService opportunityService;

    public ApplicationResponseDto toResponse(Application application) {

        ApplicationResponseDto dto = new ApplicationResponseDto();

        dto.setApplicationId(application.getApplicationId());
        dto.setApplicant(profileService.getProfileByProfileId(application.getProfileId()));
        dto.setOpportunityTitle(
                opportunityService.getOpportunityTitle(application.getOpportunityId()));
        dto.setCompanyName(
                opportunityService.getCompanyName(application.getOpportunityId()));
        dto.setApplicationDate(application.getApplicationDate());
        dto.setStatus(application.getStatus());

        return dto;
    }
}