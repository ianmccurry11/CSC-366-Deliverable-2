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
    name = "owner"
)
public class Owner {
    @Column(name="person_id")
    private Person person_id;

    @Column(name="company_name")
    private String company_name;
    
    @Column(name="company_ownings")
    private String company_ownings;

    @OneToMany(mappedBy = "owner",       // join column should be in *Address*
               cascade = CascadeType.ALL, // all JPA actions (persist, remove, refresh, merge, detach) propagate to each address
               orphanRemoval = true,      // address records that are no longer attached to a person are removed
               fetch = FetchType.LAZY)
    //@OrderColumn(name = "list_idx")
    private List<Address> companies = new ArrayList<>();

    @ManyToMany(mappedBy = "employee")
    private Set<Owner> employees = new HashSet<>();

    @OneToOne(mappedBy= "person",
              cascade = CascadeType.ALL,
              orphanRemoval = true,      
              fetch = FetchType.LAZY)
    @JoinColumn(name = "id")
    private Owner owner;

    
    public Owner() { }
    
    public Owner(Person person, String companyName, float companyOwnings) {
	this.person_id = person.id;
    this.company_name = companyName;
    this.company_ownings = companyOwnings;
    }
    
    @Override
    public String toString() {
	StringJoiner sj = new StringJoiner("," , Owner.class.getSimpleName() + "[" , "]");
	sj.add(id.toString()).add(first_name).add(last_name).add("comapnies="+companies.toString());
	return sj.toString();
    }

    public String getCompanyName() {
	return this.company_name;
    }
    public void setCompanyName(String name) {
	this.company_ownings = name;
    }
    public String getCompanyOwnings() {
	return this.company_ownings;
    }
    public void setCompanyOwnings(float ownings) {
	this.company_ownings = ownings;
    }

    @Override
    public int hashCode() {
	return 366;
    }
    
}