package com.ejada.job_portal.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Entity
@Table(name = "JOBS")
@PrimaryKeyJoinColumn(name = "OPPORTUNITY_ID")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@SuperBuilder
public class Job extends Opportunity {

    @Column(name = "SALARY")
    private double salary;

    @Column(name = "WORK_MODE", length = 20)
    private String workMode;
}