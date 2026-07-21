package com.ejada.job_portal.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "COMPANIES")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Company {

    @Id
    @Column(name = "COMPANY_ID")
    private Long companyId;

    @Column(name = "USER_ID", nullable = false)
    private Long userId;

    @Column(name = "COMPANY_NAME", nullable = false, length = 100)
    private String companyName;

    @Column(name = "IS_APPROVED", nullable = false, length = 1)
    private String isApproved;
}