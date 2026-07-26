package com.ejada.job_portal.repository;

import com.ejada.job_portal.entity.UserSkill;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserSkillRepository extends JpaRepository<UserSkill, Long> {

    List<UserSkill> findByProfileId(Long profileId);
    boolean existsByProfileIdAndSkillId(Long profileId, Long skillId);
    List<UserSkill> findBySkillId(Long skillId);

}