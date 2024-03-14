package csc366.jpademo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.repository.query.Param;

import java.util.List;

@Repository
public interface CompanyRepository extends JpaRepository<Company, Long> {

    List<Company> findByName(String name);

    List<Company> findByCountry(String country);

    @Query("SELECT c FROM Company c WHERE c.name = :name")
    List<Company> findByNameJPQL(@Param("name") String name);

    @Query("SELECT c FROM Company c WHERE c.country = :country")
    List<Company> findByCountryJPQL(@Param("country") String country);

    @Modifying
    @Query("UPDATE Company c SET c.name = :newName WHERE c.name = :oldName")
    int updateCompanyNameByOldNameJPQL(@Param("oldName") String oldName, @Param("newName") String newName);

    @Query("SELECT c FROM Company c WHERE c.name = :name OR c.country = :name")
    List<Company> findByNameOrCountryJPQL(@Param("name") String name);
}
