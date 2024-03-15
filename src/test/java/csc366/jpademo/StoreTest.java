package csc366.jpademo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.hibernate.jdbc.WorkExecutor;
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
import java.util.Date;
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
public class StoreTest {

    private final static Logger log = LoggerFactory.getLogger(StoreTest.class);
    
    @Autowired
    private StoreRepository storeRepository;

    @Autowired
    private CompanyRepository companyRepository;

    @Autowired
    private CustomerOrderRepository customerOrderRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private WorkSchedulingRepository workSchedulingRepository;

    @Autowired
    private SupplierRepository supplierRepository;

    @Autowired
    private ItemRepository itemRepository;


    private final Store store = new Store("Store 1", 1234, "SLO", "California");
    private final Company company= new Company("test", "test");
    private final Employee emp = new Employee("test@gmail.com", "1234 Street St., City, State", "123456789", "John", "Appleseed", 123456, store);
    private final Date date = new Date();
    private final WorkScheduling workSchedule = new WorkScheduling(emp, date, date, store);
    private final Customer customer = new Customer("John", "Appleseed", "email@email.com", "Address", "123456789", true, "credit card");
    private final CustomerOrder order = new CustomerOrder(customer, store, date, 3.4, "credit card");
    private final Supplier supplier = new Supplier("test", "test", 0);
    private final Item item = new Item("test", 0, 0, 0, 0, "test", false);
    private final Contract contract = new Contract(supplier, store, item);
    
    
    @BeforeEach
    private void setup() {

	storeRepository.saveAndFlush(store);
    companyRepository.saveAndFlush(company);
    employeeRepository.saveAndFlush(emp);
    workSchedulingRepository.saveAndFlush(workSchedule);
    customerRepository.saveAndFlush(customer);
    customerOrderRepository.saveAndFlush(order);
    supplierRepository.saveAndFlush(supplier);
    itemRepository.saveAndFlush(item);

    store.setCompany(company);
    company.addStore(store);
    emp.setStore(store);
    store.addWorkSchedule(workSchedule);
    store.addContract(contract);
    store.addEmployee(emp);
    store.addOrder(order);
    workSchedule.setEmpID(emp);
    workSchedule.setStoreID(store);
    order.setCustomer(customer);
    order.setStore(store);

    storeRepository.saveAndFlush(store);
    companyRepository.saveAndFlush(company);
    employeeRepository.saveAndFlush(emp);
    workSchedulingRepository.saveAndFlush(workSchedule);
    customerRepository.saveAndFlush(customer);
    customerOrderRepository.saveAndFlush(order);
    

    // contract.setStore(store);
    // // store.addContract(contract);
    // store.addContract(contract);
    // store.addEmployee(emp);
    // store.addOrder(order);
    // store.addWorkSchedule(workSchedule);

    }
    
    @Test
    @Order(1)
    public void testSaveStore() {
	Store store2 = storeRepository.findByName("Store 1");

	log.info(store2.toString());
	
	assertNotNull(store);
	assertEquals(store2.getName(), store.getName());
	assertEquals(store2.getStoreNumber(), store.getStoreNumber());
    }
    
    @Test
    @Order(2)
    public void testGetStore() {
	Store store2 = storeRepository.findByName("Store 1");

	assertNotNull(store);
	assertEquals(store2.getName(), store.getName());
	assertEquals(store2.getStoreNumber(), store.getStoreNumber());
    }

    @Test
    @Order(3)
    public void testDeleteStore() {
	storeRepository.delete(store);
	storeRepository.flush();
    }
    
    @Test
    @Order(4)
    public void testFindAllStores() {
	assertNotNull(storeRepository.findAll());
    }
    
    @Test
    @Order(5)
    public void testDeletByStoreId() {
	Store s = storeRepository.findByName("Store 1");
	storeRepository.deleteById(s.getId());
	storeRepository.flush();
    }

    @Test
    @Order(6)
    public void testJpqlFinder() {
	Store s = storeRepository.findByNameJpql("Store 1");
	assertEquals(s.getName(), s.getName());
    }

    @Test
    @Order(7)
    public void testSqlFinder() {
	Store s = storeRepository.findByNameSql("Store 1");
	assertEquals(s.getName(), s.getName());
    }

    // @Test
    // @Order(8)
    // public void testStoreandContract(){
    // Store store2 = storeRepository.findByName("Store 1");

    // log.info(store2.toString());
        
    // assertNotNull(store2);
    // assertEquals(store2.getContracts().size(), 1); 
    // }

    @Test
    @Order(9)
    public void testStoreandEmployees(){
    Store store2 = storeRepository.findByName("Store 1");

    log.info(store2.toString());
        
    assertNotNull(store2);
    assertEquals(store2.getEmployees().size(), 1); 
    }

    @Test
    @Order(10)
    public void testStoreandOrders(){
    Store store2 = storeRepository.findByName("Store 1");

    log.info(store2.toString());
        
    assertNotNull(store2);
    assertEquals(store2.getCustomerOrders().size(), 1); 
    }

    @Test
    @Order(11)
    public void testStoreandSchedules(){
    Store store2 = storeRepository.findByName("Store 1");

    log.info(store2.toString());
        
    assertNotNull(store2);
    assertEquals(store2.getWorkSchedule().size(), 1); 
    }

    @Test
    @Order(12)
    public void testJpqlJoin() {
	Store s = storeRepository.findByNameWithContractsJpql("Store 1");
	log.info(s.toString());

	s.addContract(new Contract(supplier, s, item));
	storeRepository.saveAndFlush(s);

	s = storeRepository.findByNameWithContractsJpql("Store 1");
	log.info(s.toString());
    }

    @Test
    @Order(13)
    public void testJpqlJoin2() {
	Store s = storeRepository.findByNameWithCustomerOrderJpql("Store 1");
	log.info(s.toString());

	s.addOrder(new CustomerOrder(customer, s, date, 0, "credit"));
	storeRepository.saveAndFlush(s);

	s = storeRepository.findByNameWithCustomerOrderJpql("Store 1");
	log.info(s.toString());
    }

    @Test
    @Order(14)
    public void testJpqlJoin3() {
	Store s = storeRepository.findByNameWithSchedulesJpql("Store 1");
	log.info(s.toString());

	s.addWorkSchedule(new WorkScheduling(emp, date, date, s));
	storeRepository.saveAndFlush(s);

	s = storeRepository.findByNameWithSchedulesJpql("Store 1");
	log.info(s.toString());
    }

    @Test
    @Order(15)
    public void testJpqlJoin4() {
	Store s = storeRepository.findByNameWithEmployeesJpql("Store 1");
	log.info(s.toString());

	s.addEmployee(new Employee("test", "test", "test", "test", "test", 0, s));
	storeRepository.saveAndFlush(s);

	s = storeRepository.findByNameWithEmployeesJpql("Store 1");
	log.info(s.toString());
    }


}
