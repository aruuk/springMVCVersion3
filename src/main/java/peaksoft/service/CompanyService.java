package peaksoft.service;

import org.springframework.stereotype.Service;
import peaksoft.model.Company;

import java.io.IOException;
import java.util.List;

@Service
public interface CompanyService {

    void saveCompany(Company company) ;

    List<Company> getAllCompanies(Long id);

    Company getCompanyById(Long id);

    void deleteCompany(Long id);

    void updateCompany(Company company, Long id);
}
