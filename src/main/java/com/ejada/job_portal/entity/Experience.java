package com.ejada.job_portal.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Entity
@Table(name = "EXPERIENCES")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Experience {

    @Id
    @Column(name = "EXPERIENCE_ID")
    private Long experienceId;

    @Column(name = "PROFILE_ID", nullable = false)
    private Long profileId;

    @Column(name = "JOB_TITLE", nullable = false, length = 100)
    private String jobTitle;

    @Column(name = "COMPANY_NAME", nullable = false, length = 100)
    private String companyName;

    @Temporal(TemporalType.DATE)
    @Column(name = "START_DATE", nullable = false)
    private Date startDate;

    @Temporal(TemporalType.DATE)
    @Column(name = "END_DATE")
    private Date endDate;

    @Column(name = "IS_CURRENT", length = 1)
    private String isCurrent;
}