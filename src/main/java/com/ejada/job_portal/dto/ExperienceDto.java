package com.ejada.job_portal.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ExperienceDto {

    private Long experienceId;

    @NotBlank
    private String jobTitle;

    @NotBlank
    private String companyName;

    private Date startDate;

    private Date endDate;

    private Character isCurrent;
}