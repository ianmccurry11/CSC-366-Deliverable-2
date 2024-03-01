package csc366.jpademo;
import java.util.List;
import java.util.ArrayList;
import java.util.StringJoiner;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Column;
import javax.persistence.OrderColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.CascadeType;
import javax.persistence.FetchType;
import javax.persistence.UniqueConstraint;

import javax.validation.constraints.NotNull;

@Entity
@Table (name="contracts")
public class Contract {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "supplier_id")
    private Supplier supplier;

    // @OneToOne(fetch = FetchType.LAZY)
    // @JoinColumn(name = "store_id")
    // private Store store;

    // @OneToOne(fetch = FetchType.LAZY)
    // @JoinColumn(name = "item_id")
    // private Item item;

    public Contract() {}

    

    public long getId(){
        return this.id;
        
    }

    public Supplier getSupplier(){
        return this.supplier;
    }

    public void setSupplier(Supplier supplier){
        this.supplier = supplier;
    }

    // public Store getStore(){
    //     return this.store;
    // }

    // public void setStore(Store store){
    //     this.store = store;
    // }

    // public Item getItem(){
    //     return this.item;
    // }

    // public void setItem(Item item){
    //     this.item = item;
    // }
        

}
