package com.ejada.job_portal.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Entity
@Table(name = "APPLICATIONS")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Application {

    @Id
    @Column(name = "APPLICATION_ID")
    private Long applicationId;

    @Column(name = "PROFILE_ID", nullable = false)
    private Long profileId;

    @Column(name = "OPPORTUNITY_ID", nullable = false)
    private Long opportunityId;

    @Temporal(TemporalType.DATE)
    @Column(name = "APPLICATION_DATE")
    private Date applicationDate;

    @Column(name = "STATUS", length = 20)
    private String status;
}