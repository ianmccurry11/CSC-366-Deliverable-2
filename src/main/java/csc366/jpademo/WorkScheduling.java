package csc366.jpademo;

import java.util.List;
import java.util.ArrayList;
import java.util.StringJoiner;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Column;
import javax.persistence.OrderColumn;
import javax.persistence.OneToMany;
import javax.persistence.CascadeType;
import javax.persistence.FetchType;
import javax.persistence.UniqueConstraint;

import javax.validation.constraints.NotNull;

@Entity  // indicates that this class maps to a database table
@Table(
    name = "employee",     // may be omitted for default table naming
)
public class WorkScheduling {


    private Employee employee;

    private LocalDateTime startTime;

    private LocalDateTime endTime;

    private Store store;

    
    public WorkScheduling(Employee employee, LocalDateTime startTime, LocalDateTime endTime, Store store) {
        this.employee = employee;
        this.startTime = startTime;
        this.endTime = endTime;
        this.store = store;
    }
    
    public Employee(Employee) {
	    super(firstName, lastName, email, SSN, store);
        this.contract = contract;
    }

    public Employee getEmployee() { return employee; }
    public void setEmployee(Employee employee) { this.employee = employee; }
    public Store getStore() { return store; }
    public void setStore(Store store) { this.store = store; }
    public LocalDateTime getStartTime() { return startTime; }
    public void setStartTime(LocalDateTime startTime) {this.startTime = startTime; }
    public LocalDateTime getEndTime() { return endTime; }
    public void setEndTime(LocalDateTime endTime) { this.endTime = endTime; }

    @Override
    public String toString() {
	StringJoiner sj = new StringJoiner("," , Person.class.getSimpleName() + "[" , "]");
	sj.add(id.toString()).add(firstName).add(lastName).add("addresses="+addresses.toString());
	return sj.toString();
    }

    @Override
    public int hashCode() {
	return 366;
    }
    
}
