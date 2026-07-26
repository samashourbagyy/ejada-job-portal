package com.ejada.job_portal.Mapper;

import com.ejada.job_portal.dto.request.JobRequestDto;
import com.ejada.job_portal.dto.response.JobResponseDto;
import com.ejada.job_portal.entity.Job;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface JobMapper {

    Job toEntity(JobRequestDto request);

    JobResponseDto toResponse(Job job);
}