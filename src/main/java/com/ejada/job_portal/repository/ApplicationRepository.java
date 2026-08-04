package com.ejada.job_portal.repository;

import com.ejada.job_portal.entity.Application;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ApplicationRepository extends JpaRepository<Application, Long> {

    List<Application> findByProfileId(Long profileId);

    List<Application> findByOpportunityId(Long opportunityId);

    Optional<Application> findByProfileIdAndOpportunityId(
            Long profileId,
            Long opportunityId);

    Integer countByOpportunityId(Long opportunityId);
}