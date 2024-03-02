package csc366.jpademo;

import java.util.List;
import java.util.ArrayList;
import java.util.StringJoiner;
import java.util.Date;

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
    name = "work_scheduling"
)
public class Owner {
    @NotNull
    @Column(name="emp_id")
    private Employee emp_id;
    
    @NotNull
    @Column(name="start_time")
    private Date start_time;

    @NotNull
    @Column(name="end_time")
    private Date end_time;
    
    @Column(name="store_id")
    private int store_id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "emp_id", referencedColumnName = "id")
    private Employee employee;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "store_id", referencedColumnName = "id")
    private Store store;
    
    public WorkScheduling() { }
    
    public WorkScheduling(int emp_id, Date start_time, Date end_time, int store_id) {
	this.emp_id = emp_id.id;
    this.start_time = start_time;
    this.end_time = end_time;
    this.store_id = store_id;
    }

    public int getEmpID() {
	return this.emp_id;
    }
    public void setEmpID(int id) {
	this.emp_id = id;
    }
    
    public Date getStartTime() {
	return this.start_time;
    }
    public void setStartTime(Date start) {
	this.start_time = start;
    }

    public Date getEndTime() {
	return this.end_time;
    }
    public void setEndTime(Date end) {
	this.end_time = end;
    }

    public int getStoreID() {
	return this.store_id;
    }
    public void setStoreID(int id) {
	this.store_id = id;
    }

    @Override
    public int hashCode() {
	return 366;
    }
    
}