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
public class ItemTest {

    private final static Logger log = LoggerFactory.getLogger(ItemTest.class);
    
    @Autowired
    private ItemRepository itemRepository;

    private final Item item = new Item("test", 1.2, 10);
    private final Contract contract = new Contract();

    
    
    @BeforeEach
    private void setup() {
	itemRepository.saveAndFlush(item);
    contract.setItem(item);
    itemRepository.saveAndFlush(item);

    }
    
    @Test
    @Order(1)
    public void testSaveItem() {
	Item item2 = itemRepository.findByName("test");

	log.info(item2.toString());
	
	assertNotNull(item);
	assertEquals(item2.getName(), item.getName());
	assertEquals(item2.getCost(), item.getCost());
    }
    
    @Test
    @Order(2)
    public void testGetItem() {
	Item item2 = itemRepository.findByName("test");

	assertNotNull(item);
	assertEquals(item2.getName(), item.getName());
	assertEquals(item2.getCost(), item.getCost());
    }

    @Test
    @Order(3)
    public void testDeleteItem() {
	itemRepository.delete(item);
	itemRepository.flush();
    }
    
    @Test
    @Order(4)
    public void testFindAllItems() {
	assertNotNull(itemRepository.findAll());
    }
    
    @Test
    @Order(5)
    public void testDeletById() {
	Item s = itemRepository.findByName("test");
	itemRepository.deleteById(s.getId());
	itemRepository.flush();
    }

    @Test
    @Order(6)
    public void testJpqlFinder() {
	Item s = itemRepository.findByNameJpql("test");
	assertEquals(s.getName(), item.getName());
    }

    @Test
    @Order(7)
    public void testSqlFinder() {
	Item s = itemRepository.findByNameSql("test");
	assertEquals(s.getName(), item.getName());
    }


}