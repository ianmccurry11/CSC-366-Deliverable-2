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

import java.util.Date;

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
public class CustomerOrderTest {

    private final static Logger log = LoggerFactory.getLogger(CustomerOrderTest.class);

    @Autowired
    private CustomerOrderRepository customerOrderRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private StoreRepository storeRepository;

    private final Customer customer = new Customer("John", "Doe", "john.doe@email.com", "123 Main St", "555-1234", true, "Credit Card");

    private final Store store = new Store("Test Store", 123, "Test City", "Test State");

    private final CustomerOrder customerOrder = new CustomerOrder(customer, store, new Date(), 100.0, "Credit Card");

    @BeforeEach
    private void setup() {
        storeRepository.saveAndFlush(store);
        customerRepository.saveAndFlush(customer);
        customerOrderRepository.saveAndFlush(customerOrder);
    }

    @Test
    @Order(1)
    public void testSaveCustomerOrder() {
        CustomerOrder savedOrder = customerOrderRepository.findById(customerOrder.getOrderID()).orElse(null);

        assertNotNull(savedOrder);
        assertEquals(customerOrder.getCustomer().getFirstName(), savedOrder.getCustomer().getFirstName());
        assertEquals(customerOrder.getStore().getName(), savedOrder.getStore().getName());
        // Add more assertions for other attributes
    }

    @Test
    @Order(2)
    public void testGetCustomerOrder() {
        CustomerOrder retrievedOrder = customerOrderRepository.findById(customerOrder.getOrderID()).orElse(null);

        assertNotNull(retrievedOrder);
        assertEquals(customerOrder.getCustomer().getFirstName(), retrievedOrder.getCustomer().getFirstName());
        assertEquals(customerOrder.getStore().getName(), retrievedOrder.getStore().getName());
        // Add more assertions for other attributes
    }

    @Test
    @Order(3)
    public void testDeleteCustomerOrder() {
        customerOrderRepository.delete(customerOrder);
        customerOrderRepository.flush();
    }

    @Test
    @Order(4)
    public void testFindAllCustomerOrders() {
        assertNotNull(customerOrderRepository.findAll());
    }

    @Test
    @Order(5)
    public void testFindByCustomerFirstName() {
        assertNotNull(customerOrderRepository.findByCustomerFirstName("John"));
    }
}
