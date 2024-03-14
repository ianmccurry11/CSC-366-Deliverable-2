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
public class CustomerTest {

    private final static Logger log = LoggerFactory.getLogger(CustomerTest.class);

    @Autowired
    private CustomerRepository customerRepository;

    private final Customer customer = new Customer("John", "Doe", "john.doe@email.com", "123 Main St", "555-1234", true, "Credit Card");

    @BeforeEach
    public void setup() {
        customerRepository.saveAndFlush(customer);
    }

    @Test
    @Order(1)
    public void testSaveCustomer() {
        Customer savedCustomer = customerRepository.findByFirstName("John");

        log.info(savedCustomer.toString());

        assertNotNull(savedCustomer);
        assertEquals(customer.getFirstName(), savedCustomer.getFirstName());
        assertEquals(customer.getLastName(), savedCustomer.getLastName());
        assertEquals(customer.getEmail(), savedCustomer.getEmail());
        // Add more assertions for other attributes
    }

    @Test
    @Order(2)
    public void testGetCustomer() {
        Customer retrievedCustomer = customerRepository.findByFirstName("John");

        assertNotNull(retrievedCustomer);
        assertEquals(customer.getFirstName(), retrievedCustomer.getFirstName());
        assertEquals(customer.getLastName(), retrievedCustomer.getLastName());
        assertEquals(customer.getEmail(), retrievedCustomer.getEmail());
        // Add more assertions for other attributes
    }

    @Test
    @Order(3)
    public void testDeleteCustomer() {
        customerRepository.delete(customer);
        customerRepository.flush();
    }

    @Test
    @Order(4)
    public void testFindAllCustomers() {
        assertNotNull(customerRepository.findAll());
    }

    @Test
    @Order(5)
    public void testDeleteByCustomerId() {
        Customer c = customerRepository.findByFirstName("John");
        customerRepository.deleteById(c.getCustomerID());
        customerRepository.flush();
    }

    @Test
    @Order(6)
    public void testJpqlFinder() {
        Customer c = customerRepository.findByNameJpql("John");
        assertEquals(c.getFirstName(), customer.getFirstName());
    }

    @Test
    @Order(7)
    public void testSqlFinder() {
        Customer c = customerRepository.findByNameSql("John");
        assertEquals(c.getFirstName(), customer.getFirstName());
    }
}
