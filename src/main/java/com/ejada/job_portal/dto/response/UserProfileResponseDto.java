package com.ejada.job_portal.dto.response;

import com.ejada.job_portal.dto.ExperienceDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserProfileResponseDto {

    private Long profileId;
    private Long userId;

    private String firstName;
    private String lastName;
    private String university;
    private BigDecimal gpa;
    private String accountType;

    private List<String> skills;

    private List<ExperienceDto> experiences;
}
