package com.ejada.job_portal.service;

import com.ejada.job_portal.entity.Skill;
import com.ejada.job_portal.repository.SkillRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SkillService {

    private final SkillRepository skillRepository;

    @Transactional
    public List<Skill> getOrCreateSkills(List<String> skillNames) {

        List<Skill> skills = new ArrayList<>();

        for (String name : skillNames) {

            Skill skill = skillRepository
                    .findBySkillNameIgnoreCase(name.trim())
                    .orElseGet(() -> {

                        Skill newSkill = new Skill();
                        newSkill.setSkillName(name.trim());

                        return skillRepository.save(newSkill);
                    });

            skills.add(skill);
        }

        return skills;
    }
}