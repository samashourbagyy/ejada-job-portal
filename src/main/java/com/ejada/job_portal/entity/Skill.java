package com.ejada.job_portal.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "SKILLS")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Skill {

    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "skills_seq_generator"
    )
    @SequenceGenerator(
            name = "skills_seq_generator",
            sequenceName = "SKILLS_SEQ",
            allocationSize = 1
    )
    @Column(name = "SKILL_ID")
    private Long skillId;

    @Column(name = "SKILL_NAME", nullable = false, unique = true, length = 100)
    private String skillName;
}