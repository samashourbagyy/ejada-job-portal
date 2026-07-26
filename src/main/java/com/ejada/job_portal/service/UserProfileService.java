package com.ejada.job_portal.service;

import com.ejada.job_portal.Mapper.UserProfileMapper;
import com.ejada.job_portal.dto.request.UserProfileRequestDto;
import com.ejada.job_portal.dto.response.UserProfileResponseDto;
import com.ejada.job_portal.entity.UserProfile;
import com.ejada.job_portal.exception.ResourceNotFoundException;
import com.ejada.job_portal.repository.UserProfileRepository;
import com.ejada.job_portal.repository.UserRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserProfileService {

    private final UserProfileRepository userProfileRepository;
    private final UserRepository userRepository;
    private final UserProfileMapper userProfileMapper;
    private final UserSkillService userSkillService;
    private final ExperienceService experienceService;

    @PreAuthorize("hasRole('USER')")
    @Transactional
    public void createProfile(
            @Valid UserProfileRequestDto request,
            Long userId) {

        if (userProfileRepository.findByUserId(userId).isPresent()) {
            throw new IllegalArgumentException("User profile already exists");
        }
        if (userRepository.findById(userId).isEmpty()) {
            throw new ResourceNotFoundException("No user found");
        }
        UserProfile profile = userProfileMapper.toEntity(request);

        profile.setUserId(userId);

        userProfileRepository.save(profile);

        userSkillService.saveSkills(
                profile.getProfileId(),
                request.getSkills());

        experienceService.saveExperiences(
                profile.getProfileId(),
                request.getExperiences());
    }

    @PreAuthorize("hasRole('USER')")
    @Transactional
    public void updateProfile(
            @Valid UserProfileRequestDto request,
            Long userId) {

        UserProfile profile = userProfileRepository.findByUserId(userId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("User profile not found"));

        profile.setFirstName(request.getFirstName());
        profile.setLastName(request.getLastName());
        profile.setUniversity(request.getUniversity());
        profile.setGpa(request.getGpa());
        profile.setAccountType(request.getAccountType());

        userProfileRepository.save(profile);

        userSkillService.saveSkills(
                profile.getProfileId(),
                request.getSkills());

        experienceService.saveExperiences(
                profile.getProfileId(),
                request.getExperiences());
    }

    public UserProfileResponseDto getProfileByUserId(Long userId) {

        UserProfile profile = userProfileRepository.findByUserId(userId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("User profile not found"));

        UserProfileResponseDto response =
                userProfileMapper.toResponse(profile);

        response.setSkills(
                userSkillService.getSkillsByUserProfileId(
                        profile.getProfileId()));

        response.setExperiences(
                experienceService.getExperiencesByProfileId(
                        profile.getProfileId()));

        return response;
    }

    @PreAuthorize("hasRole('ADMIN')")
    public List<UserProfileResponseDto> getAllProfiles() {

        return userProfileRepository.findAll()
                .stream()
                .map(profile -> {

                    UserProfileResponseDto response =
                            userProfileMapper.toResponse(profile);

                    response.setSkills(
                            userSkillService.getSkillsByUserProfileId(
                                    profile.getProfileId()));

                    response.setExperiences(
                            experienceService.getExperiencesByProfileId(
                                    profile.getProfileId()));

                    return response;

                })
                .toList();
    }
    public UserProfileResponseDto getProfileByProfileId(Long profileId) {

        UserProfile profile = userProfileRepository.findById(profileId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("User profile not found"));

        UserProfileResponseDto response =
                userProfileMapper.toResponse(profile);

        response.setSkills(
                userSkillService.getSkillsByUserProfileId(profileId));

        response.setExperiences(
                experienceService.getExperiencesByProfileId(profileId));

        return response;
    }
}