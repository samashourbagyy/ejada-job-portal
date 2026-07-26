package com.ejada.job_portal.Mapper;

import com.ejada.job_portal.dto.request.UserProfileRequestDto;
import com.ejada.job_portal.dto.response.UserProfileResponseDto;
import com.ejada.job_portal.entity.UserProfile;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserProfileMapper {

    UserProfile toEntity(UserProfileRequestDto dto);

    UserProfileResponseDto toResponse(UserProfile profile);
}
