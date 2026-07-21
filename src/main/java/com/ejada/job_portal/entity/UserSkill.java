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
    @Column(name = "USER_SKILL_ID")
    private Long userSkillId;

    @Column(name = "PROFILE_ID", nullable = false)
    private Long profileId;

    @Column(name = "SKILL_ID", nullable = false)
    private Long skillId;
}