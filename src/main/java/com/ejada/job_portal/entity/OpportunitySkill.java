package com.ejada.job_portal.entity;

import jakarta.persistence.*;
import lombok.*;
@Entity
@Table(name = "OPPORTUNITY_SKILLS")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OpportunitySkill {

    @Id
    @Column(name = "OPPORTUNITY_SKILL_ID")
    private Long opportunitySkillId;

    @Column(name = "OPPORTUNITY_ID", nullable = false)
    private Long opportunityId;

    @Column(name = "SKILL_ID", nullable = false)
    private Long skillId;
}