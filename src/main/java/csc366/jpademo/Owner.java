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
    @NotNull
    @Column(unique=true, name="person_id")
    private Person person_id;
    
    @NotNull
    @Column(name="company_name")
    private Company company_name;
    
    @Column(name="company_ownings")
    private String company_ownings;

    @OneToMany(mappedBy = "owner",       // join column should be in *Company*
               orphanRemoval = true,      // company records that are no longer attached to an owner are removed
               fetch = FetchType.LAZY)
    private List<Company> companies = new ArrayList<>();

    @OneToOne(cascade = CascadeType.ALL,
              fetch = FetchType.LAZY)
    @JoinColumn(name = "id", referencedColumnName = "person_id")
    private Person person;
    
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
    public Float getCompanyOwnings() {
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