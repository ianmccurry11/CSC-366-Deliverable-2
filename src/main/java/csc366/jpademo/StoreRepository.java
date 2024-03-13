package csc366.jpademo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.repository.query.Param;

import java.util.List;

@Repository
public interface StoreRepository extends JpaRepository<Store, Long> {

    // Query inferred from method name, code generated by Spring Framework
    Store findByName(String name);

    // JPQL query
    @Query("from Store s where s.name = :name")
    Store findByNameJpql(@Param("name") String name);

    // JPQL query with join (assuming there is an 'addresses' field in the Customer entity)
    @Query("select s from Store s join s.contracts ctr where s.name = :name")
    Store findByNameWithContractsJpql(@Param("name") String name);

    @Query("select s from Store s join s.employees ctr where s.name = :name")
    Store findByNameWithEmployeesJpql(@Param("name") String name);


    @Query("select s from Store s join s.orders ctr where s.name = :name")
    Store findByNameWithCustomerOrderJpql(@Param("name") String name);
    
    @Query("select s from Store s join s.WorkSchedules sch where s.name = :name")
    Store findByNameWithSchedulesJpql(@Param("name") String name);
    

    // Native SQL query (validity not checked, invalid SQL will cause runtime exception)
    @Query(value = "select * from store as s where s.name = :name ", nativeQuery = true)
    Store findByNameSql(@Param("name") String name);

    @Modifying
    @Query("update Store s set s.name = :newName where s.name = :oldName")
    void updateName(@Param("oldName") String oldName, @Param("newName") String newName);

    // Add more custom queries based on your requirements

}
