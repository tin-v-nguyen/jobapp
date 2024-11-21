package com.tin.jobapp.company.service;

import com.tin.jobapp.company.model.Company;

import java.util.List;

public interface ICompanyService {
    List<Company> findAllCompanies();
    Company createCompany(Company company);
    Company updateCompany(Long id, Company company);

    Boolean deleteCompanyById(Long id);
    Company findCompanyById(Long id);
}
