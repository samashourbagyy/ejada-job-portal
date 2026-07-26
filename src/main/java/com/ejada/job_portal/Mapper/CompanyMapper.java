package com.ejada.job_portal.Mapper;

import com.ejada.job_portal.dto.request.CompanyRequestDto;
import com.ejada.job_portal.dto.response.CompanyResponseDto;
import com.ejada.job_portal.entity.Company;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CompanyMapper {

    Company toEntity(CompanyRequestDto request);

    CompanyResponseDto toResponse(Company company);
}