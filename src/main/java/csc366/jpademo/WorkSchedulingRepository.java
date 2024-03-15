package csc366.jpademo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.repository.query.Param;
import java.util.Date;

import java.util.List;

@Repository
public interface WorkSchedulingRepository extends JpaRepository<WorkScheduling, Long> {

    
    // JPQL query
    @Query("from WorkScheduling s where s.start_time = :date")
    WorkScheduling findByStartDateJpql(@Param("date") Date date);

    @Query("select s from WorkScheduling s join s.employee emp where s.employee = :employee")
    WorkScheduling findByDateWithEmployeepql(@Param("employee") Employee employee);

    // Native SQL query (validity not checked, invalid SQL will cause runtime exception)
    @Query(value = "select * from workscheduling as s where s.start_time = :date ", nativeQuery = true)
    WorkScheduling findByDateSql(@Param("date") Date date);

    @Modifying
    @Query("update WorkScheduling s set s.start_time = :newDate where s.start_time = :oldDate")
    void updateName(@Param("oldDate") Date oldDate, @Param("newDate") Date newDate);

    // Add more custom queries based on your requirements

}
