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
public class JobResponseDto {

    private Long opportunityId;

    private String companyName;

    private String title;

    private String description;

    private String location;

    private Date deadline;

    private Integer maxApplicants;

    private String status;

    private Double salary;

    private String employmentType;
}