package csc366.jpademo;

import java.util.List;
import java.util.ArrayList;
import java.util.StringJoiner;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Table;
import javax.persistence.Column;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.CascadeType;
import javax.persistence.FetchType;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;

@Entity
@Table (name="employee")

public class Employee {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private long id;

    @Column(name="first_name")
    private String FirstName;

    @Column(name="last_name")
    private String LastName;

    @Column(name="email")
    private String Email;

    @Column(name="address")
    private String Address;

    @Column(name="phone_no")
    private String PhoneNo;
    
    @Column(name="SSN")
    private int SSN;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="store_id")
    private Store store;

    // @OneToOne(fetch = FetchType.LAZY)
    // @JoinColumn(name = "EmpllId")
    // private Employee employee;

    @OneToMany(mappedBy = "employee",       // join column should be in *Address*
               cascade = CascadeType.ALL, // all JPA actions (persist, remove, refresh, merge, detach) propagate to each address
               orphanRemoval = true,
               fetch = FetchType.LAZY      // address records that are no longer attached to a person are removed
               )
    private List<PayStub> payStubs = new ArrayList<>();

    @OneToMany(mappedBy = "employee",       // join column should be in *Address*
               cascade = CascadeType.ALL, // all JPA actions (persist, remove, refresh, merge, detach) propagate to each address
               orphanRemoval = true,
               fetch = FetchType.LAZY      // address records that are no longer attached to a person are removed
               )
    private List<WorkScheduling> workSchedulings = new ArrayList<>();

    @OneToMany(mappedBy = "employee",       // join column should be in *Address*
               cascade = CascadeType.ALL, // all JPA actions (persist, remove, refresh, merge, detach) propagate to each address
               orphanRemoval = true ,
               fetch = FetchType.LAZY     // address records that are no longer attached to a person are removed
               )
    private List<EmploymentHistory> employmentHistories = new ArrayList<>();


    
    public Employee(String Email, String Address, String PhoneNo, String FirstName, String LastName, int SSN, Store StoreID) {
	this.FirstName = FirstName;
	this.LastName = LastName;
	this.Email = Email;
    this.Address = Address;
    this.PhoneNo = PhoneNo;
    this.SSN = SSN;
    this.store = StoreID;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String Email) {
        this.Email = Email;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        this.Address = address;
    }

    public String getPhoneNo() {
        return PhoneNo;
    }

    public void setPhoneNo(String PhoneNo) {
        this.PhoneNo = PhoneNo;
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

    public int getSSN() {
        return SSN;
    }

    public void setSSN(int sSN) {
        SSN = sSN;
    }

    public Store getStore() {
        return store;
    }

    public void setStore(Store storeId) {
        store = storeId;
    }


}
