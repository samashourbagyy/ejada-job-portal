package com.ejada.job_portal.service;

import com.ejada.job_portal.Mapper.ExperienceMapper;
import com.ejada.job_portal.dto.ExperienceDto;
import com.ejada.job_portal.entity.Experience;
import com.ejada.job_portal.exception.ResourceNotFoundException;
import com.ejada.job_portal.repository.ExperienceRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ExperienceService {

    private final ExperienceRepository experienceRepository;
    private final ExperienceMapper experienceMapper;

    @Transactional
    public void saveExperiences(
            Long profileId,
            List<ExperienceDto> experiences) {

        if (experiences == null || experiences.isEmpty()) {
            return;
        }

        for (ExperienceDto dto : experiences) {

            Experience experience = experienceMapper.toEntity(dto);

            experience.setProfileId(profileId);

            experienceRepository.save(experience);
        }
    }

    public List<ExperienceDto> getExperiencesByProfileId(Long profileId) {

        return experienceRepository.findByProfileId(profileId)
                .stream()
                .map(experienceMapper::toDto)
                .toList();
    }

    public ExperienceDto getExperienceById(Long experienceId) {

        Experience experience = experienceRepository.findById(experienceId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Experience not found"));

        return experienceMapper.toDto(experience);
    }
}