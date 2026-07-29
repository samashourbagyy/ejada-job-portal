package com.ejada.job_portal.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ApplicationResponseDto {


        Long applicationId;
        UserProfileResponseDto applicant;
        String opportunityTitle;
        String companyName;
        Date applicationDate;
        String status;

}
