package csc366.jpademo;

import javax.persistence.*;

import javax.validation.constraints.NotNull;

@Entity  // indicates that this class maps to a database table
@Table(
    name = "employee"     // may be omitted for default table naming
)
public class Employee extends Person {

    @NotNull
    @Column(unique=true, name="SSN")
    private int SSN;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "store", nullable = true)
    private Store store;

    public Employee(){super();}

    public Employee(String firstName, String lastName, String email, String phoneNo, String username, String address,
                    int SSN, Store store) {
	    super(firstName, lastName, email, phoneNo, username, address);
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
        return super.toString();
    }

    @Override
    public int hashCode() {
	return 366;
    }
    
}
