package csc366.jpademo;

import static org.junit.jupiter.api.Assertions.*;
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
public class CustomerRepositoryTest {

    private final static Logger log = LoggerFactory.getLogger(CustomerRepositoryTest.class);

    @Autowired
    private CustomerRepository customerRepository;

    private final Customer customer = new Customer(true, "test", new PersonR("test", "test", "test"));

    @BeforeEach
    private void setup() {
        customerRepository.saveAndFlush(customer);
    }

    @Test
    @Order(1)
    public void testSaveCustomer() {
        Customer customer2 = customerRepository.findByFirstName("test");

        log.info(customer2.toString());

        assertNotNull(customer);
        assertEquals(customer2.getFirstName(), customer.getFirstName());
        assertEquals(customer2.getLastName(), customer.getLastName());
    }

    @Test
    @Order(2)
    public void testGetCustomerById() {
        Customer retrievedCustomer = customerRepository.findById(customer.getCustomerID()).orElse(null);

        assertNotNull(retrievedCustomer);
        assertEquals(customer.getFirstName(), retrievedCustomer.getFirstName());
        assertEquals(customer.getLastName(), retrievedCustomer.getLastName());
    }

    @Test
    @Order(3)
    public void testUpdateCustomer() {
        Customer retrievedCustomer = customerRepository.findByFirstName("test");

        assertNotNull(retrievedCustomer);

        // Update customer details
        retrievedCustomer.setFirstName("updatedFirstName");
        retrievedCustomer.setLastName("updatedLastName");

        customerRepository.saveAndFlush(retrievedCustomer);

        // Fetch the updated customer
        Customer updatedCustomer = customerRepository.findByFirstName("updatedFirstName");

        assertNotNull(updatedCustomer);
        assertEquals("updatedFirstName", updatedCustomer.getFirstName());
        assertEquals("updatedLastName", updatedCustomer.getLastName());
    }

    @Test
    @Order(4)
    public void testDeleteCustomer() {
        customerRepository.delete(customer);
        customerRepository.flush();

        Customer deletedCustomer = customerRepository.findByFirstName("test");

        assertNull(deletedCustomer);
    }

    // Add more tests as needed

    // For example:
    // @Test
    // @Order(5)
    // public void testFindAllCustomers() {
    //     List<Customer> customers = customerRepository.findAll();
    //     assertNotNull(customers);
    //     assertTrue(customers.size() > 0);
    // }
}
