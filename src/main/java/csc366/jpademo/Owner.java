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
import javax.persistence.OneToOne;
import javax.persistence.CascadeType;
import javax.persistence.FetchType;
import javax.persistence.UniqueConstraint;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

@Entity  // indicates that this class maps to a database table
@Table(
    name = "owner"
)
public class Owner {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name="Email")
    private String Email;

    @Column(name="Address")
    private String Address;

    @Column(name="PhoneNo")
    private String PhoneNo;

    @Column(name="FirstName")
    private String FirstName;

    @Column(name="LastName")
    private String LastName;
   
    @Column(name="CompanyName")
    private String CompanyName;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "company_id", nullable = true)
    private Company company;

    // @OneToMany(mappedBy = "owner",       // join column should be in *Company*
    //            orphanRemoval = true,      // company records that are no longer attached to an owner are removed
    //            fetch = FetchType.LAZY)
    // private List<Company> companies = new ArrayList<>();

    // @OneToOne(cascade = CascadeType.ALL,
    //           fetch = FetchType.LAZY)
    // @JoinColumn(name = "id", referencedColumnName = "person_id")
    // private Owner owner;
    
    public Owner() { }
    
    public Owner(String FirstName, String LastName, String Email, String Address, String PhoneNo, String CompanyName) {
        this.FirstName = FirstName;
        this.LastName = LastName;
        this.Address = Address;
        this.Email = Email;
        this.PhoneNo = PhoneNo;
        this.CompanyName = CompanyName;
    }
    
    // @Override
    // public String toString() {
	// StringJoiner sj = new StringJoiner("," , Owner.class.getSimpleName() + "[" , "]");
	// sj.add(id.toString()).add(first_name).add(last_name).add("comapnies="+companies.toString());
	// return sj.toString();
    // }

   

    @Override
    public int hashCode() {
	return 366;
    }

    public long getOwnerID() {
        return id;
    }

    public void setOwnerID(long ownerID) {
        id = ownerID;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public String getPhoneNo() {
        return PhoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        PhoneNo = phoneNo;
    }

    public String getFirstName() {
        return FirstName;
    }

    public void setFirstName(String firstName) {
        FirstName = firstName;
    }

    public String getLastName() {
        return LastName;
    }

    public void setLastName(String lastName) {
        LastName = lastName;
    }

    public String getCompanyName() {
        return CompanyName;
    }

    public void setCompanyName(String companyName) {
        CompanyName = companyName;
    }

    // public List<Company> getCompanies() {
    //     return companies;
    // }

    // public void setCompanies(List<Company> companies) {
    //     this.companies = companies;
    // }

    
}