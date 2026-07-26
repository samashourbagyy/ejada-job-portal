package com.ejada.job_portal.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Entity
@Table(name = "USER_PROFILES")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserProfile {

    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "user_profiles_seq_generator"
    )
    @SequenceGenerator(
            name = "user_profiles_seq_generator",
            sequenceName = "USER_PROFILES_SEQ",
            allocationSize = 1
    )
    @Column(name = "PROFILE_ID")
    private Long profileId;

    @Column(name = "USER_ID", nullable = false)
    private Long userId;

    @Column(name = "FIRST_NAME", nullable = false, length = 50)
    private String firstName;

    @Column(name = "LAST_NAME", nullable = false, length = 50)
    private String lastName;

    @Column(name = "UNIVERSITY", length = 100)
    private String university;

    @Column(name = "GPA")
    private BigDecimal gpa;

    @Column(name = "ACCOUNT_TYPE", nullable = false, length = 20)
    private String accountType;
}