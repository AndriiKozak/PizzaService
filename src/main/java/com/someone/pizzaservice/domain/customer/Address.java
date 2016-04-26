/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.someone.pizzaservice.domain.customer;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author Andrii_Kozak1
 */
@Entity
@Table(name="Addresses")
public class Address {
    @Id @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int id;
    private String address;
    
    public Address(){
        this.address="";
    }

    public Address(String address) {
        this.address = address;
    }

    /**
     * @return the address
     */
    public String getAdress() {
        return address;
    }

    /**
     * @param adress the address to set
     */
    public void setAddress(String adress) {
        this.address = adress;
    }

    @Override
    public String toString() {
        return "Address{" + "address=" + address + '}';
    }

}
