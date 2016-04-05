/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.someone.pizzaservice.domain.customer;

/**
 *
 * @author Andrii_Kozak1
 */
public class Address {

    private String address;

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
