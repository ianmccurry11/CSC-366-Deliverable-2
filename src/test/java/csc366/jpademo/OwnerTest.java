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
public class OwnerTest {

    private final static Logger log = LoggerFactory.getLogger(OwnerTest.class);

    @Autowired
    private OwnerRepository ownerRepository;

    private final Owner owner = new Owner("John", "Doe", "john.doe@email.com", "123 Main St", "555-1234", "ABC Company");

    @BeforeEach
    public void setup() {
        ownerRepository.saveAndFlush(owner);
    }

    @Test
    @Order(1)
    public void testSaveOwner() {
        Owner savedOwner = ownerRepository.findByFirstName("John");

        log.info(savedOwner.toString());

        assertNotNull(savedOwner);
        assertEquals(owner.getFirstName(), savedOwner.getFirstName());
        assertEquals(owner.getLastName(), savedOwner.getLastName());
        assertEquals(owner.getEmail(), savedOwner.getEmail());
        // Add more assertions for other attributes
    }

    @Test
    @Order(2)
    public void testGetOwner() {
        Owner retrievedOwner = ownerRepository.findByFirstName("John");

        assertNotNull(retrievedOwner);
        assertEquals(owner.getFirstName(), retrievedOwner.getFirstName());
        assertEquals(owner.getLastName(), retrievedOwner.getLastName());
        assertEquals(owner.getEmail(), retrievedOwner.getEmail());
        // Add more assertions for other attributes
    }

    @Test
    @Order(3)
    public void testDeleteOwner() {
        ownerRepository.delete(owner);
        ownerRepository.flush();
    }

    @Test
    @Order(4)
    public void testFindAllOwners() {
        assertNotNull(ownerRepository.findAll());
    }

    @Test
    @Order(5)
    public void testDeleteByOwnerId() {
        Owner o = ownerRepository.findByFirstName("John");
        ownerRepository.deleteById(o.getOwnerID());
        ownerRepository.flush();
    }

    @Test
    @Order(6)
    public void testJpqlFinder() {
        Owner o = ownerRepository.findByNameJpql("John");
        assertEquals(o.getFirstName(), owner.getFirstName());
    }

    @Test
    @Order(7)
    public void testSqlFinder() {
        Owner o = ownerRepository.findByNameSql("John");
        assertEquals(o.getFirstName(), owner.getFirstName());
    }
}
