package csc366.jpademo;

import java.util.Set;
import java.util.Date;
import java.util.HashSet;
import java.util.StringJoiner;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Column;
import javax.persistence.ManyToOne;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.UniqueConstraint;

import javax.validation.constraints.NotNull;

@Entity  // this class maps to a database table
public class PayStub {
    @Id
    private Date pay_period;   // note: no annotation, still included in underlying table
    private int overtime;
    private int wage;
    private double taxes;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "empl_id", nullable = true)
    private Employee empl_id;
    
    public PayStub() { }
    
    public PayStub(Date pay_period, int overtime, int wage, double taxes) {
	this.pay_period = pay_period;
	this.overtime = overtime;
	this.wage = wage;
	this.taxes = taxes;
    }
    
    public int getOvertime() {
	return overtime;
    }
    public void setOverTime(int overtime) {
	this.overtime = overtime;
    }

    public Date getpay_period() {
        return pay_period;
    }

    public void setpay_period(Date pay_period) {
        this.pay_period = pay_period;
    }

    public int getWage() {
	return wage;
    }
    public void setWage(int wage) {
	this.wage = wage;
    }

    public double gettaxes() {
	return taxes;
    }
    public void settaxes(double taxes) {
	this.taxes = taxes;
    }

    public Employee getEmpl_id() {
	return empl_id;
    }
    public void setEmpl_id(Employee empl_id) {
	this.empl_id = empl_id;
    }
        
    @Override
    public String toString()
    {
	StringJoiner sj = new StringJoiner("," , PayStub.class.getSimpleName() + "[" , "]");
	sj.add(empl_id.toString()).add(pay_period.toString()).add(String.valueOf(wage)).add(String.valueOf(taxes)).add(String.valueOf(overtime));
	return sj.toString();
    }

    @Override
    public boolean equals(Object o) {
	if (this == o) return true;
	if (!(o instanceof PayStub)) return false;
	return empl_id != null && empl_id.equals(((PayStub) o).getEmpl_id()) && pay_period != null && pay_period.equals(((PayStub) o).getpay_period());
    }

    @Override
    public int hashCode() {
	return 366;
    }
    
}
