package csc366.jpademo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.TestPropertySource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@TestPropertySource(properties = {
        "spring.datasource.url=jdbc:h2:mem:testdb",
        "spring.datasource.driverClassName=org.h2.Driver",
        "spring.datasource.username=sa",
        "spring.datasource.password=password",
        "spring.jpa.database-platform=org.hibernate.dialect.H2Dialect",
        "spring.h2.console.enabled=true"
})
@TestMethodOrder(OrderAnnotation.class)
public class CompanyTest {

    private final static Logger log = LoggerFactory.getLogger(CompanyTest.class);

    @Autowired
    private CompanyRepository companyRepository;

    private final Company company = new Company("Example Company", "United States");

    @BeforeEach
    public void setup() {
        companyRepository.saveAndFlush(company);
    }

    @Test
    @Order(1)
    public void testSaveCompany() {
        Company savedCompany = companyRepository.findByName("Example Company").get(0);

        log.info(savedCompany.toString());

        assertNotNull(savedCompany);
        assertEquals(company.getName(), savedCompany.getName());
        assertEquals(company.getCountry(), savedCompany.getCountry());
        // Add more assertions for other attributes
    }

    @Test
    @Order(2)
    public void testGetCompany() {
        Company retrievedCompany = companyRepository.findByName("Example Company").get(0);

        assertNotNull(retrievedCompany);
        assertEquals(company.getName(), retrievedCompany.getName());
        assertEquals(company.getCountry(), retrievedCompany.getCountry());
        // Add more assertions for other attributes
    }

    @Test
    @Order(3)
    public void testDeleteCompany() {
        companyRepository.delete(company);
        companyRepository.flush();
    }

    @Test
    @Order(4)
    public void testFindAllCompanies() {
        List<Company> companies = companyRepository.findAll();
        assertNotNull(companies);
        assertEquals(1, companies.size()); // Assuming only one company is saved in the setup
    }

//    @Test
//    @Order(5)
//    public void testUpdateCompanyName() {
//        String newName = "Updated Company Name";
//        int updatedCount = companyRepository.updateCompanyNameByOldNameJPQL("Example Company", newName);
//        assertEquals(1, updatedCount); // Ensure that one record is updated
//        Company updatedCompany = companyRepository.findByName(newName).get(0);
//        assertEquals(newName, updatedCompany.getName());
//    }

    @Test
    @Order(6)
    public void testFindCompanyByNameOrCountry() {
        List<Company> companies = companyRepository.findByNameOrCountryJPQL("Example Company");
        assertNotNull(companies);
        assertEquals(1, companies.size()); // Assuming only one company is saved in the setup
    }
}
