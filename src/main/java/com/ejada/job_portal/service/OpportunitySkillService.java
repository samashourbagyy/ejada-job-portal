package com.ejada.job_portal.service;

import com.ejada.job_portal.entity.OpportunitySkill;
import com.ejada.job_portal.entity.Skill;
import com.ejada.job_portal.exception.ResourceNotFoundException;
import com.ejada.job_portal.repository.OpportunitySkillRepository;
import com.ejada.job_portal.repository.SkillRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OpportunitySkillService {

    private final OpportunitySkillRepository opportunitySkillRepository;
    private final SkillRepository skillRepository;

    @Transactional
    public void saveSkills(Long opportunityId, List<String> skillNames) {

        if (skillNames == null || skillNames.isEmpty()) {
            return;
        }

        for (String skillName : skillNames) {

            String trimmed = skillName.trim();

            Skill skill = skillRepository
                    .findBySkillNameIgnoreCase(trimmed)
                    .orElseGet(() -> {

                        Skill newSkill = new Skill();
                        newSkill.setSkillName(trimmed);

                        return skillRepository.save(newSkill);
                    });

            OpportunitySkill opportunitySkill = new OpportunitySkill();

            opportunitySkill.setOpportunityId(opportunityId);
            opportunitySkill.setSkillId(skill.getSkillId());

            opportunitySkillRepository.save(opportunitySkill);
        }
    }

    public List<String> getSkillsByOpportunityId(Long opportunityId) {

        return opportunitySkillRepository.findByOpportunityId(opportunityId)
                .stream()
                .map(opportunitySkill -> skillRepository.findById(opportunitySkill.getSkillId())
                        .orElseThrow(() ->
                                new ResourceNotFoundException("Skill not found"))
                        .getSkillName())
                .toList();
    }
}