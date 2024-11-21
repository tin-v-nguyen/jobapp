package com.tin.jobapp.job.repository;

import com.tin.jobapp.job.model.Job;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JobRepository extends JpaRepository<Job, Long> {
}
