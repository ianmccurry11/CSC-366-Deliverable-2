package csc366.jpademo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.repository.query.Param;

import java.util.List;

@Repository
public interface CustomerOrderRepository extends JpaRepository<CustomerOrder, Long> {

    @Query("from CustomerOrder co where co.customer.FirstName = :name")
    List<CustomerOrder> findByCustomerFirstName(@Param("name") String name);

    @Query("from CustomerOrder co where co.customer.FirstName = :name or co.customer.LastName = :name")
    List<CustomerOrder> findByCustomerNameJpql(@Param("name") String name);

    @Query("select co from CustomerOrder co join co.customer c where c.FirstName = :name or c.LastName = :name")
    List<CustomerOrder> findByCustomerNameWithJoinJpql(@Param("name") String name);

    @Query(value = "select * from customer_order as co " +
            "inner join customer as c on co.customer_id = c.id " +
            "where c.first_name = :name or c.last_name = :name", nativeQuery = true)
    List<CustomerOrder> findByCustomerNameSql(@Param("name") String name);

    @Modifying
    @Query("update CustomerOrder co set co.customer.FirstName = :newName where co.customer.FirstName = :oldName")
    void updateCustomerFirstName(@Param("oldName") String oldName, @Param("newName") String newName);

}
