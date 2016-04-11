/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.someone.pizzaservice.domain.customer;

import com.someone.pizzaservice.domain.discountcard.DCard;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 *
 * @author Andrii_Kozak1
 */
@Component("customer")
@Scope("prototype")
public class Customer {

    private static int sId = 0;
    private int id;
    private String name;
    private Address address;
    private DCard dCard;

    public Customer() {
    }

    public Customer(String name, Address adress) {
        this.id = sId++;
        this.name = name;
        this.address = adress;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {

        return id;
    }

    @Override
    public String toString() {
        return "Customer{"
                + "id=" + id
                + ", name='" + name + '\''
                + ", address='" + address + '\''
                + '}';
    }

    /**
     * @return the address
     */
    public Address getAddress() {
        return address;
    }

    /**
     * @param adress the adress to set
     */
    public void setAdress(Address address) {
        this.address = address;
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
}
