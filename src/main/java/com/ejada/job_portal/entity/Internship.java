package com.ejada.job_portal.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Entity
@Table(name = "INTERNSHIPS")
@PrimaryKeyJoinColumn(name = "OPPORTUNITY_ID")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@SuperBuilder
public class Internship extends Opportunity {

    @Column(name = "DURATION_MONTHS", nullable = false)
    private Integer durationMonths;

    @Column(name = "IS_PAID", length = 1)
    private String isPaid;
}