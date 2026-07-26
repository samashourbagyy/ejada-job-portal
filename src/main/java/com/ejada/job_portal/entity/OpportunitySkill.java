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
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "opportunity_skills_seq_generator"
    )
    @SequenceGenerator(
            name = "opportunity_skills_seq_generator",
            sequenceName = "OPPORTUNITY_SKILLS_SEQ",
            allocationSize = 1
    )
    @Column(name = "OPPORTUNITY_SKILL_ID")
    private Long opportunitySkillId;

    @Column(name = "OPPORTUNITY_ID", nullable = false)
    private Long opportunityId;

    @Column(name = "SKILL_ID", nullable = false)
    private Long skillId;
}