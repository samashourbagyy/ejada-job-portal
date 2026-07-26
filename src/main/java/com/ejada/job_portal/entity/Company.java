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
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "companies_seq_generator"
    )
    @SequenceGenerator(
            name = "companies_seq_generator",
            sequenceName = "COMPANIES_SEQ",
            allocationSize = 1
    )
    @Column(name = "COMPANY_ID")
    private Long companyId;

    @Column(name = "USER_ID", nullable = false)
    private Long userId;

    @Column(name = "COMPANY_NAME", nullable = false, length = 100)
    private String companyName;

    @Column(name = "INDUSTRY", nullable = false, length = 100)
    private String industry;
}