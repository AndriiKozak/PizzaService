/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.someone.pizzaservice.domain.pizza;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;

/**
 *
 * @author Andrii_Kozak1
 */
@Entity
public class Pizza {
    @Id 
    private long id;
    private String name;
    private Double price;
    @Enumerated(EnumType.STRING)
    @Column(name="pizzatype")
    private PizzaType type;

    
    public Pizza(){}
    public Pizza(int id, String name, Double price, PizzaType type) {
        this.name = name;
        this.price = price;
        this.type = type;
        this.id = id;
    }

    /**
     * @return the id
     */
    public long getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(long id) {
        this.id = id;
    }

    /**
     * @param id the id to set
     */
    /**
     * @return the price
     */
    public Double getPrice() {
        return price;
    }

    /**
     * @param price the price to set
     */
    public void setPrice(Double price) {
        this.price = price;
    }

    /**
     * @return the type
     */
    public PizzaType getType() {
        return type;
    }

    /**
     * @param type the type to set
     */
    public void setType(PizzaType type) {
        this.type = type;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    public String toString() {
        return "Pizza{"
                + "id=" + id
                + ", name='" + name + '\''
                + ", price=" + price
                + ", type=" + type
                + '}';
    }

}
