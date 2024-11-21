package com.tin.jobapp.company.service;

import com.tin.jobapp.company.model.Company;
import com.tin.jobapp.company.repository.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CompanyService implements ICompanyService {

    @Autowired
    private CompanyRepository companyRepository;

    @Override
    public List<Company> findAllCompanies() {
        return companyRepository.findAll();
    }

    @Override
    public Company createCompany(Company company) {
        return companyRepository.save(company);
    }

    @Override
    public Company updateCompany(Long id, Company company) {
        Optional<Company> oldCompanyOpt = companyRepository.findById(id);
        if (oldCompanyOpt.isPresent()) {
            Company oldCompany = oldCompanyOpt.get();
            oldCompany.setDescription(company.getDescription());
            oldCompany.setName(company.getName());
            oldCompany.setJobs(company.getJobs());
            return companyRepository.save(oldCompany);
        }
        return null;
    }

    @Override
    public Boolean deleteCompanyById(Long id) {
        if (companyRepository.existsById(id)) {
            companyRepository.deleteById(id);
            return true;
        }
        return false;
    }

    @Override
    public Company findCompanyById(Long id) {
        return companyRepository.findById(id).orElse(null);
    }
}
