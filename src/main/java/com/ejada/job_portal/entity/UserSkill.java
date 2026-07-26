package com.ejada.job_portal.entity;

import jakarta.persistence.*;
import lombok.*;
@Entity
@Table(name = "USER_SKILLS")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserSkill {
    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "user_skills_seq_generator"
    )
    @SequenceGenerator(
            name = "user_skills_seq_generator",
            sequenceName = "USER_SKILLS_SEQ",
            allocationSize = 1
    )
    @Column(name = "USER_SKILL_ID")
    private Long userSkillId;

    @Column(name = "PROFILE_ID", nullable = false)
    private Long profileId;

    @Column(name = "SKILL_ID", nullable = false)
    private Long skillId;
}