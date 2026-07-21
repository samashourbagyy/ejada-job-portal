package com.ejada.job_portal.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@MappedSuperclass
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public abstract class Opportunity {

    @Id
    @Column(name = "OPPORTUNITY_ID")
    protected Long opportunityId;

    @Column(name = "COMPANY_ID", nullable = false)
    protected Long companyId;

    @Column(name = "TITLE", nullable = false)
    protected String title;

    @Column(name = "DESCRIPTION")
    protected String description;

    @Column(name = "LOCATION")
    protected String location;

    @Temporal(TemporalType.DATE)
    @Column(name = "DEADLINE", nullable = false)
    protected Date deadline;

    @Column(name = "MAX_APPLICANTS", nullable = false)
    protected Integer maxApplicants;

    @Column(name = "STATUS")
    protected String status;
}