package csc366.jpademo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.repository.query.Param;

import java.util.List;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {

    @Query("from Customer c where c.FirstName = :name")
    Customer findByFirstName(@Param("name") String name);

    @Query("from Customer c where c.FirstName = :name or c.LastName = :name")
    Customer findByNameJpql(@Param("name") String name);

    @Query("select c from Customer c where c.FirstName = :name or c.LastName = :name")
    Customer findByNameWithAddressJpql(@Param("name") String name);

    @Query(value = "select * from customer as c where c.first_name = :name or c.last_name = :name", nativeQuery = true)
    Customer findByNameSql(@Param("name") String name);

    @Modifying
    @Query("update Customer c set c.FirstName = :newName where c.FirstName = :oldName")
    void updateName(@Param("oldName") String oldName, @Param("newName") String newName);
}
