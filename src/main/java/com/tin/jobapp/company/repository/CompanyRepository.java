package com.tin.jobapp.company.repository;

import com.tin.jobapp.company.model.Company;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompanyRepository extends JpaRepository<Company, Long> {
}
