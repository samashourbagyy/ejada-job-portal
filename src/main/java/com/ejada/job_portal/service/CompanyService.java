package com.ejada.job_portal.service;

import com.ejada.job_portal.Mapper.CompanyMapper;
import com.ejada.job_portal.dto.request.CompanyRequestDto;
import com.ejada.job_portal.dto.response.CompanyResponseDto;
import com.ejada.job_portal.entity.Company;
import com.ejada.job_portal.entity.User;
import com.ejada.job_portal.exception.DuplicateResourceException;
import com.ejada.job_portal.exception.ResourceNotFoundException;
import com.ejada.job_portal.repository.CompanyRepository;
import com.ejada.job_portal.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.BadRequestException;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CompanyService {

    private final CompanyRepository companyRepository;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final CompanyMapper companyMapper;

    public Company getCompanyByUserId(Long userId) {
        return companyRepository.findByUserId(userId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Company not found"));
    }

    public CompanyResponseDto getCompanyById(Long companyId) {

        Company company = companyRepository.findById(companyId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Company not found"));

        return companyMapper.toResponse(company);
    }

    public String getCompanyName(Long companyId) {
        return getCompanyById(companyId).getCompanyName();
    }
    @Transactional
    @PreAuthorize("hasRole('ADMIN')")
    public void createCompany(CompanyRequestDto request) throws BadRequestException {

        if (userRepository.findByEmail(request.getEmail()).isPresent()) {
            throw new DuplicateResourceException("Email already exists");
        }
        User user = User.builder()
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role("COMPANY")
                .build();

        userRepository.save(user);

        Company company = Company.builder()
                .userId(user.getUserId())
                .companyName(request.getCompanyName())
                .industry(request.getIndustry())
                .build();

        companyRepository.save(company);
    }

    public List<CompanyResponseDto> getAllCompanies() {


            return companyRepository.findAll()
                    .stream()
                    .map(company -> {

                        CompanyResponseDto response =
                                companyMapper.toResponse(company);

                        User user = userRepository.findById(company.getUserId())
                                .orElseThrow(() ->
                                        new ResourceNotFoundException("User not found"));

                        return response;
                    })
                    .toList();
        }
    }
