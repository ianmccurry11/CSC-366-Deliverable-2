package csc366.jpademo;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "customer_order")
public class CustomerOrder {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "store_id")
    private Store store;

    @Column(name = "order_date")
    private Date orderDate;

    @Column(name = "total_amount")
    private double totalAmount;

    @Column(name = "payment_type")
    private String paymentType;

    public CustomerOrder(Customer customer, Store store, Date orderDate, double totalAmount, String paymentType) {
        this.customer = customer;
        this.store = store;
        this.orderDate = orderDate;
        this.totalAmount = totalAmount;
        this.paymentType = paymentType;
    }

    
    public Long getOrderID() {
        return id;
    }

    public void setOrderID(Long orderID) {
        this.id = orderID;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Store getStore() {
        return store;
    }

    public void setStore(Store store) {
        this.store = store;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public String getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(String paymentType) {
        this.paymentType = paymentType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CustomerOrder)) return false;
        CustomerOrder that = (CustomerOrder) o;
        return Double.compare(getTotalAmount(), that.getTotalAmount()) == 0 && Objects.equals(getOrderID(),
                that.getOrderID()) && Objects.equals(getCustomer(), that.getCustomer()) &&
                Objects.equals(store, that.store) && Objects.equals(getOrderDate(), that.getOrderDate()) &&
                Objects.equals(getPaymentType(), that.getPaymentType());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getOrderID(), getCustomer(), getStore(), getOrderDate(), getTotalAmount(), getPaymentType());
    }
}
