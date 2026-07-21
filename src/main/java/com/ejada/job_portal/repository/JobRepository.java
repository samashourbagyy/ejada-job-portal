package com.ejada.job_portal.repository;

import com.ejada.job_portal.entity.Internship;
import com.ejada.job_portal.entity.Job;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface JobRepository extends JpaRepository<Job, Long> {
    List<Internship> findByCompanyId(Long companyId);
}