package csc366.jpademo;

import java.util.Set;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.StringJoiner;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Column;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.UniqueConstraint;

import javax.validation.constraints.NotNull;

@Entity  // this class maps to a database table

public class EmploymentHistory {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private long id;
    
    private LocalDate date;   // note: no annotation, still included in underlying table
    private boolean status;
    private int salary;
    private String title;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "employee_id", referencedColumnName = "id")
    private Employee employee;
    
    public EmploymentHistory() {}
    
    public EmploymentHistory(LocalDate date, boolean status, int salary, String title) {
	this.date = date;
	this.status = status;
	this.salary = salary;
	this.title = title;
    }
    
    public boolean getStatus() {
	return status;
    }
    public void setStatus(boolean status) {
	this.status = status;
    }

    public LocalDate getdate() {
        return date;
    }

    public void setdate(LocalDate date) {
        this.date = date;
    }

    public int getSalary() {
	return salary;
    }
    public void setCity(int salary) {
	this.salary = salary;
    }

    public String getTitle() {
	return title;
    }
    public void setTitle(String title) {
	this.title = title;
    }

    public Employee getEmpl_id() {
	return employee;
    }
    public void setEmpl_id(Employee empl_id) {
	this.employee = empl_id;
    }
        
    @Override
    public String toString()
    {
	StringJoiner sj = new StringJoiner("," , EmploymentHistory.class.getSimpleName() + "[" , "]");
	sj.add(employee.toString()).add(date.toString()).add(String.valueOf(salary)).add(title).add(String.valueOf(status));
	return sj.toString();
    }

    @Override
    public boolean equals(Object o) {
	if (this == o) return true;
	if (!(o instanceof EmploymentHistory)) return false;
	return employee != null && employee.equals(((EmploymentHistory) o).getEmpl_id()) && date != null && date.equals(((EmploymentHistory) o).getdate());
    }

    @Override
    public int hashCode() {
	return 366;
    }
    
}
