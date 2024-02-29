package csc366.jpademo;

import java.util.List;
import java.util.ArrayList;
import java.util.StringJoiner;

import javax.persistence.*;

import javax.validation.constraints.NotNull;

@Entity
@Table(
        name = "Customer"
)
public class Customer {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long customerID;

    @Column(name="RewardsOption")
    private boolean rewardsOption;

    @Column(name="PaymentType")
    private String paymentType;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "customerID", referencedColumnName = "ID")
    private Person person;
}
