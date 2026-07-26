package com.ejada.job_portal.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OpportunityRequestDto {

    @NotBlank
    private String title;

    private String description;

    private String location;

    @NotNull
    private Date deadline;

    @NotNull
    private Integer maxApplicants;

    private List<String> skills;
}