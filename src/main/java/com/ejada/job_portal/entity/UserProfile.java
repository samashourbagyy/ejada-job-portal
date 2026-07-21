package com.ejada.job_portal.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "USER_PROFILES")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserProfile {

    @Id
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
    private Double gpa;

    @Column(name = "ACCOUNT_TYPE", nullable = false, length = 20)
    private String accountType;
}