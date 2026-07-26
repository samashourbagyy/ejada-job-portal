package com.ejada.job_portal.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.Date;

@Entity
@Table(name = "OPPORTUNITIES")
@Inheritance(strategy = InheritanceType.JOINED)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder

public abstract class Opportunity {

    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "opportunities_seq_generator"
    )
    @SequenceGenerator(
            name = "opportunities_seq_generator",
            sequenceName = "OPPORTUNITIES_SEQ",
            allocationSize = 1
    )
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