package com.ejada.job_portal.service;

import com.ejada.job_portal.entity.Skill;
import com.ejada.job_portal.entity.UserSkill;
import com.ejada.job_portal.exception.ResourceNotFoundException;
import com.ejada.job_portal.repository.SkillRepository;
import com.ejada.job_portal.repository.UserSkillRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserSkillService {

    private final UserSkillRepository userSkillRepository;
    private final SkillRepository skillRepository;

    @Transactional
    public void saveSkills(Long userProfileId, List<String> skillNames) {

        if (skillNames == null || skillNames.isEmpty()) {
            return;
        }

        for (String skillName : skillNames) {

            String normalized = skillName.trim();

            Skill skill = skillRepository
                    .findBySkillNameIgnoreCase(normalized)
                    .orElseGet(() -> {

                        Skill newSkill = new Skill();
                        newSkill.setSkillName(normalized);

                        return skillRepository.save(newSkill);
                    });

            UserSkill userSkill = new UserSkill();

            userSkill.setProfileId(userProfileId);
            userSkill.setSkillId(skill.getSkillId());

            userSkillRepository.save(userSkill);
        }
    }

    public List<String> getSkillsByUserProfileId(Long userProfileId) {

        return userSkillRepository.findByProfileId(userProfileId)
                .stream()
                .map(userSkill -> skillRepository.findById(userSkill.getSkillId())
                        .orElseThrow(() ->
                                new ResourceNotFoundException("Skill not found"))
                        .getSkillName())
                .toList();
    }

    @Transactional
    public void deleteByProfileId(Long profileId) {
        userSkillRepository.deleteByProfileId(profileId);
    }
}