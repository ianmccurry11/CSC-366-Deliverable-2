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
    name = "locationManager"     // may be omitted for default table naming
)
public class LocationManager extends Employee {

    @OneToMany(mappedBy = "person",
            cascade = CascadeType.ALL,
            orphanRemoval = true,
            fetch = FetchType.LAZY)
    private List<Contract> contracts = new ArrayList<>();

    public LocationManager() { }
    
    public LocationManager(String firstName, String lastName, String email, String phoneNo, String username,
                           int SSN, Store store) {
	    super(firstName, lastName, email, phoneNo, username, SSN, store);
    }

    public void addContract(Contract c) {
        contracts.add(c);
        c.setLocationManager(this);
    }
    public void removeContract(Contract c) {
        contracts.remove(c);
        c.setLocationManager(null);
    }
    public List<Contract> getContracts() {
        return this.contracts;
    }


    @Override
    public String toString() {
	StringJoiner sj = new StringJoiner("," , Person.class.getSimpleName() + "[" , "]");
	sj.add(super.getId().toString()).add(super.getFirstName()).add(super.getLastName()).add("addresses="+super.getAddresses().toString());
	return sj.toString();
    }

    @Override
    public int hashCode() {
	return 366;
    }
    
}
