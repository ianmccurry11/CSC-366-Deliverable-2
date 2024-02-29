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
public class Employee extends Person {

    @NotNull
    @Column(unique=true, name="SSN")
    private int SSN;

    @Column()
    private Store store;

    public Employee() { }
    
    public Employee(String firstName, String lastName, String email, int SSN, Store store) {
	    super(firstName, lastName, email);
        this.SSN = SSN;
        this.store = store;
    }
    
    public int getSSN() {
	return SSN;
    }
    public void setSSN(int SSN) {
	this.SSN = SSN;
    }

    public Store getStore() { return store; }
    public void setStore(Store store) {
        this.store = store;
    }


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
