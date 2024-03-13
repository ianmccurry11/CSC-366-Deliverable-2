package csc366.jpademo;

import java.util.Objects;
import java.util.StringJoiner;
import java.util.List;
import java.util.ArrayList;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.persistence.Column;
import javax.persistence.OneToOne;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.CascadeType;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;

@Entity
@Table(name = "customer",
        uniqueConstraints = @UniqueConstraint(columnNames = {"FirstName", "LastName", "Email"}))
public class Customer {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private long id;

    // @Column(name = "CustomerID")
    // private Long customerID;
    // @GeneratedValue(strategy = GenerationType.IDENTITY)
    // private long CustomerID;

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

    @Column(name = "RewardsOption")
    private boolean RewardsOption;

    @Column(name = "PaymentType")
    private String PaymentType;

    // @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    // @JoinColumn(name = "CustomerID", referencedColumnName = "CustomerID")
    // private Customer customer;

    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CustomerOrder> customerOrders = new ArrayList<>();

    public Customer(String FirstName, String LastName, String Email, String Address, String PhoneNo, boolean RewardsOption, String PaymentType) {
        this.RewardsOption = RewardsOption;
        this.PaymentType = PaymentType;
        this.FirstName = FirstName;
        this.LastName = LastName;
        this.Address = Address;
        this.Email = Email;
        this.PhoneNo = PhoneNo;
    }

    public Long getCustomerID() {
        return id;
    }

    public void setCustomerID(Long customerID) {
        this.id = customerID;
    }

    public boolean isRewardsOption() {
        return RewardsOption;
    }

    public void setRewardsOption(boolean rewardsOption) {
        this.RewardsOption = rewardsOption;
    }

    public String getPaymentType() {
        return PaymentType;
    }

    public void setPaymentType(String paymentType) {
        this.PaymentType = paymentType;
    }

    public List<CustomerOrder> getCustomerOrders() {
        return customerOrders;
    }

    public void setCustomerOrders(List<CustomerOrder> customerOrders) {
        this.customerOrders = customerOrders;
    }

    // @Override
    // public String toString() {
    //     StringJoiner sj = new StringJoiner(",", Customer.class.getSimpleName() + "[", "]");
    //     sj.add(this.CustomerID.toString()).add(String.valueOf(this.RewardsOption)).add(this.PaymentType);
    //     return sj.toString();
    // }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Customer)) return false;
        Customer customer = (Customer) o;
        return isRewardsOption() == customer.isRewardsOption() && Objects.equals(getCustomerID(),
                customer.getCustomerID()) && Objects.equals(getPaymentType(), customer.getPaymentType());
    }

    // @Override
    // public int hashCode() {
    //     return Objects.hash(getCustomerID(), isRewardsOption(), getPaymentType());
    // }

    public String getFirstName() {
        return this.FirstName;
    }

    public String getLastName() {
        return this.LastName;
    }

    public String getEmail() {
        return this.Email;
    }
}
