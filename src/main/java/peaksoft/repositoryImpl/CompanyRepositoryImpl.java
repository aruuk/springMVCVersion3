package peaksoft.repositoryImpl;

import lombok.SneakyThrows;
import org.springframework.stereotype.Repository;
import peaksoft.model.Company;
import peaksoft.repository.CompanyRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.io.IOException;
import java.util.List;

@Repository
@Transactional
public class CompanyRepositoryImpl implements CompanyRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void saveCompany(Company company){
        entityManager.persist(company);
    }

    @Override
    public List<Company> getAllCompanies() {
        return entityManager.createQuery("select c from Company c",
                Company.class).getResultList();
    }

    @Override
    public Company getCompanyById(Long id) {
        return entityManager.createQuery("select c from Company c where " +
                "c.id =: id", Company.class).setParameter("id", id).getSingleResult();
    }

    @Override
    public void update(Company company, Long id) {
        Company company1 = entityManager.find(Company.class, id);
        company1.setCompanyName(company.getCompanyName());
        company1.setLocatedCountry(company.getLocatedCountry());
        entityManager.merge(company);
    }

    @Override
    public void deleteCompany(Long id) {
        entityManager.remove(entityManager.find(Company.class, id));
    }

}
