package csc366.jpademo;

import java.util.List;
import java.util.ArrayList;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Column;
import javax.persistence.OneToMany;
import javax.persistence.CascadeType;
import javax.persistence.FetchType;


@Entity
@Table(name="store")
public class Store {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    @Column(name="name")
    private String name;

    @Column(name = "store_number")
    private int storeNumber;

    @Column(name= "city")
    private String city;

    @Column(name="State")
    private String state;


    @OneToMany(mappedBy = "store",       // join column should be in *Address*
               cascade = CascadeType.ALL, // all JPA actions (persist, remove, refresh, merge, detach) propagate to each address
               orphanRemoval = true,      // address records that are no longer attached to a person are removed
               fetch = FetchType.LAZY)
    private List<Contract> contracts = new ArrayList<>();

    

    public long getId(){
        return id;
    }

    public void setId(long id){
        this.id = id;
    }

    public String getName(){
        return name;
    }

    public void setName(String name){
        this.name = name;
    }

    public int getStoreNumber(){
        return storeNumber;
    }

    public void setStoreNumber(int storeNumber){
        this.storeNumber = storeNumber;
    }

    public String getCity(){
        return city;
    }

    public void setCity(String city){
        this.city = city;
    }

    public String getState(){
        return state;
    }

    public void setState(String state){
        this.state = state;
    }

    public void addContract(Contract c) {
        contracts.add(c);
        c.setStore(this);
    }
    
    public void removeContract(Contract c) {
        contracts.remove(c);
        c.setStore(null);
    }
    
    public List<Contract> getContracts() {
        return this.contracts;
    }



}
