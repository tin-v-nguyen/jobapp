package com.tin.jobapp.company.controller;

import com.tin.jobapp.company.model.Company;
import com.tin.jobapp.company.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/companies")
public class CompanyController {
    @Autowired
    private CompanyService companyService;

    @GetMapping
    public ResponseEntity<List<Company>> findAllCompanies() {
        return new ResponseEntity<>(companyService.findAllCompanies(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Company> createCompany(@RequestBody Company company) {
        return new ResponseEntity<>(companyService.createCompany(company), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Company> updateCompany(@PathVariable Long id, @RequestBody Company company) {
        Company uCompany = companyService.updateCompany(id, company);
        ResponseEntity<Company> response;
        response = uCompany == null ? new ResponseEntity<>(HttpStatus.NOT_FOUND)
                : new ResponseEntity<>(uCompany, HttpStatus.OK);
        return response;
    }

    // TODO: When deleting a company, need to delete jobs from that company
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCompany(@PathVariable Long id) {
        ResponseEntity<String> response;
        response = companyService.deleteCompanyById(id) ? new ResponseEntity<>("Company successfully deleted", HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
        return response;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Company> getCompanyById(@PathVariable Long id) {
        Company company = companyService.findCompanyById(id);
        ResponseEntity<Company> response;
        response = company == null ? new ResponseEntity<>(HttpStatus.NOT_FOUND)
                : new ResponseEntity<>(company, HttpStatus.OK);
        return response;
    }
}
