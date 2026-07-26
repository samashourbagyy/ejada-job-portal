package com.ejada.job_portal.Mapper;

import com.ejada.job_portal.dto.request.InternshipRequestDto;
import com.ejada.job_portal.dto.response.InternshipResponseDto;
import com.ejada.job_portal.entity.Internship;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
    public interface InternshipMapper {

        Internship toEntity(InternshipRequestDto request);

    InternshipResponseDto toResponse(Internship internship);
}

