
    package com.ejada.job_portal.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public class CompanyResponseDto {

        private Long companyId;

        private String companyName;

        private String industry;

        private Long userId;
    }

