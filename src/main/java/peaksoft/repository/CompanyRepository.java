package peaksoft.repository;

import org.springframework.stereotype.Repository;
import peaksoft.model.Company;

import java.util.List;

@Repository
public interface CompanyRepository {

    void saveCompany(Company company);
    List<Company> getAllCompanies();
    Company getCompanyById(Long id);
    void update(Company company, Long id) ;
    void deleteCompany(Long id);
}
