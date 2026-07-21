package com.ejada.job_portal.repository;

import com.ejada.job_portal.entity.OpportunitySkill;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OpportunitySkillRepository extends JpaRepository<OpportunitySkill, Long> {

    List<OpportunitySkill> findByOpportunityId(Long opportunityId);

    List<OpportunitySkill> findBySkillId(Long skillId);

}