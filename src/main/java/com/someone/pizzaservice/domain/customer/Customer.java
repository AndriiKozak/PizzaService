/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.someone.pizzaservice.domain.customer;

import com.someone.pizzaservice.domain.discountcard.DCard;
import com.someone.pizzaservice.domain.discountcard.NoCard;
import com.someone.pizzaservice.domain.discountcard.StandartDCard;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author Andrii_Kozak1
 */
//@Domain
//@Scope("prototype")
@Entity
@Table(name = "Customers")
public class Customer implements FactoryBean<Customer> {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    private String name;
    @OneToMany(cascade = CascadeType.ALL /*,fetch = FetchType.EAGER */)
    @JoinColumn(name = "id_customer")
    private List<Address> addresses;
    @OneToOne(targetEntity = StandartDCard.class, cascade = CascadeType.ALL)
    private DCard dCard;

    public Customer() {
        dCard = NoCard.getInstance();
    }

    public Customer(String name, List<Address> addresses) {
        this.name = name;
        this.addresses = addresses;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {

        return id;
    }

    @Override
    public String toString() {
        return "Customer{"
                + "id=" + id
                + ", name='" + name + '\''
                + ", address='" + addresses + '\''
                + '}';
    }

    /**
     * @return the address
     */
    public List<Address> getAddresses() {
        return addresses;
    }

    /**
     * @param adress the adress to set
     */
    public void setAddress(List<Address> addresses) {
        this.addresses = addresses;
    }

    /**
     * @return the dcard
     */
    public DCard getDCard() {
        return dCard;
    }

    /**
     * @param dcard the dcard to set
     */
    @Autowired
    public void setDCard(DCard dCard) {
        this.dCard = dCard;
    }

    /**
     * @return the dCard
     */
    public DCard getdCard() {
        return dCard;
    }

    /**
     * @param dCard the dCard to set
     */
    public void setdCard(DCard dCard) {
        this.dCard = dCard;
    }

    @Override
    public Customer getObject() throws Exception {
        return new Customer();
    }

    @Override
    public Class<?> getObjectType() {
        return Customer.class;
    }

    @Override
    public boolean isSingleton() {
        return false;
    }
}
