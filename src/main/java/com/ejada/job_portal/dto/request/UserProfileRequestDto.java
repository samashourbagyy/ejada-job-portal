package com.ejada.job_portal.dto.request;

import com.ejada.job_portal.dto.ExperienceDto;
import jakarta.validation.constraints.NotBlank;
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
public class UserProfileRequestDto {

    @NotBlank
    private String firstName;

    @NotBlank
    private String lastName;

    private String university;

    private BigDecimal gpa;

    @NotBlank
    private String accountType;

    private List<String> skills;

    private List<ExperienceDto> experiences;
}
