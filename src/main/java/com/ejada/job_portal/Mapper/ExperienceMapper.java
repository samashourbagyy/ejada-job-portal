package com.ejada.job_portal.Mapper;

import com.ejada.job_portal.dto.ExperienceDto;
import com.ejada.job_portal.entity.Experience;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ExperienceMapper {

    Experience toEntity(ExperienceDto dto);

    ExperienceDto toDto(Experience experience);
}