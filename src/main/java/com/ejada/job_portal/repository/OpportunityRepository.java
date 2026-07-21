package com.ejada.job_portal.repository;

import com.ejada.job_portal.entity.Opportunity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OpportunityRepository extends JpaRepository<Opportunity, Long> {

    List<Opportunity> findByCompanyId(Long companyId);

    List<Opportunity> findByStatus(String status);

}