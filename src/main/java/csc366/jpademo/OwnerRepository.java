package csc366.jpademo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.repository.query.Param;

import java.util.List;

@Repository
public interface OwnerRepository extends JpaRepository<Owner, Long> {

    @Query("from Owner o where o.FirstName = :name")
    Owner findByFirstName(@Param("name") String name);

    @Query("from Owner o where o.FirstName = :name or o.LastName = :name")
    Owner findByNameJpql(@Param("name") String name);

    @Query("select o from Owner o join o.company c where o.FirstName = :name or o.LastName = :name")
    Owner findByNameWithCompanyJpql(@Param("name") String name);

    @Query(value = "select * from owner as o where o.first_name = :name or o.last_name = :name", nativeQuery = true)
    Owner findByNameSql(@Param("name") String name);

    @Modifying
    @Query("update Owner o set o.FirstName = :newName where o.FirstName = :oldName")
    void updateFirstName(@Param("oldName") String oldName, @Param("newName") String newName);

}
