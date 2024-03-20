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
import org.springframework.test.context.ContextConfiguration;
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
public class EmployeeTest {
    private final static Logger log = LoggerFactory.getLogger(EmployeeTest.class);

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private StoreRepository storeRepository;

    private final Store store = new Store("Store 1", 1234, "SLO", "California");
    private final Employee employee = new Employee("jackadams@calpoly.edu", "455 Slack St", "8056734567", "Jack", "Adams", 999999999, store);

    @BeforeEach
    private void setup(){
        storeRepository.saveAndFlush(store);
        employeeRepository.saveAndFlush(employee);
        employee.setStore(store);
        store.addEmployee(employee);
        employeeRepository.saveAndFlush(employee);
        storeRepository.saveAndFlush(store);

    }

    @Test
    @Order(1)

    public void testSaveEmployee(){
        Employee employee2 = employeeRepository.findByFirstName("Jack");
    

    log.info(employee2.toString());

    assertNotNull(employee);
    assertEquals(employee2.getFirstName(), employee.getFirstName());
    assertEquals(employee2.getPhoneNo(), employee.getPhoneNo());

    }

    @Test
    @Order(2)
    public void testGetEmployee() {
	Employee employee2 = employeeRepository.findByFirstName("Jack");

	assertNotNull(employee);
	assertEquals(employee2.getFirstName(), employee.getFirstName());
	assertEquals(employee2.getPhoneNo(), employee.getPhoneNo());
    }

    @Test
    @Order(3)
    public void testDeleteEmployee() {
	//employeeRepository.delete(employee);
	employeeRepository.flush();
    }

    @Test
    @Order(4)
    public void testFindAllEmployee() {
	assertNotNull(employeeRepository.findAll());
    }

    // @Test
    // @Order(5)
    // public void testDeletByPersonId() {
	// Supplier s = supplierRepository.findByName("test");
	// supplierRepository.deleteById(s.getId());
	// supplierRepository.flush();
    // }
}