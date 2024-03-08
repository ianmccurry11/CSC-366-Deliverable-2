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

// Demo0: Add, list, and remove Person instances

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
public class SupplierTest {

    private final static Logger log = LoggerFactory.getLogger(SupplierTest.class);
    
    @Autowired
    private SupplierRepository supplierRepository;

    private final Supplier supplier = new Supplier("test", "123 street st.", 1234567890);
    
    @BeforeEach
    private void setup() {
	supplierRepository.saveAndFlush(supplier);
    }
    
    @Test
    @Order(1)
    public void testSaveSupplier() {
	Supplier supplier2 = supplierRepository.findByName("test");

	log.info(supplier2.toString());
	
	assertNotNull(supplier);
	assertEquals(supplier2.getName(), supplier.getName());
	assertEquals(supplier2.getPhone(), supplier.getPhone());
    }
    
    @Test
    @Order(2)
    public void testGetSupplier() {
	Supplier supplier2 = supplierRepository.findByName("test");

	assertNotNull(supplier);
	assertEquals(supplier2.getName(), supplier.getName());
	assertEquals(supplier2.getPhone(), supplier.getPhone());
    }

    @Test
    @Order(3)
    public void testDeleteSupplier() {
	supplierRepository.delete(supplier);
	supplierRepository.flush();
    }
    
    @Test
    @Order(4)
    public void testFindAllSuppliers() {
	assertNotNull(supplierRepository.findAll());
    }
    
    @Test
    @Order(5)
    public void testDeletByPersonId() {
	Supplier s = supplierRepository.findByName("test");
	supplierRepository.deleteById(s.getId());
	supplierRepository.flush();
    }

    @Test
    @Order(6)
    public void testJpqlFinder() {
	Supplier s = supplierRepository.findByNameJpql("test");
	assertEquals(s.getName(), supplier.getName());
    }

    @Test
    @Order(7)
    public void testSqlFinder() {
	Supplier s = supplierRepository.findByNameSql("test");
	assertEquals(s.getName(), supplier.getName());
    }

}
