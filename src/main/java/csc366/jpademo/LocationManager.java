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
public class LocationManager extends Employee {

    @OneToMany(mappedBy = "person",       // join column should be in *Address*
            cascade = CascadeType.ALL, // all JPA actions (persist, remove, refresh, merge, detach) propagate to each address
            orphanRemoval = true,      // address records that are no longer attached to a person are removed
            fetch = FetchType.LAZY)
    //@OrderColumn(name = "list_idx")
    private List<Address> addresses = new ArrayList<>();

    public LocationManager() { }
    
    public Employee(String firstName, String lastName, String email, String phoneNo, String username, int SSN, Store store, Contract contract) {
	    super(firstName, lastName, email, SSN, store, phoneNo, username);
        this.contract = contract;
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
