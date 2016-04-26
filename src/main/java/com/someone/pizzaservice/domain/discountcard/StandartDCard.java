/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.someone.pizzaservice.domain.discountcard;

import com.someone.pizzaservice.domain.order.Order;
import java.io.Serializable;
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
@Table(name = "DCards")
public class StandartDCard implements DCard, Serializable {

    // This class do calculate dicouts for customers with dicount card, since 
    // it has all nessesary infomation to do this, in order to implement single
    // responsibility principle.
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int id;
    private double total = 0;
    final static double PERCENTAGE_OF_TOTAL_TO_DISCOUNT = 0.1;
    final static double MAXIMAL_DISCOUNT = 0.3;

    @Override
    public double getDiscountedPrice(Order order) {
        Double res = NoCard.getInstance().getDiscountedPrice(order);
        res = Math.max(res * (1 - MAXIMAL_DISCOUNT),
                res - getTotal() * PERCENTAGE_OF_TOTAL_TO_DISCOUNT);
        return res;
    }

    @Override
    public void addToAccount(Order order) {
        total += getDiscountedPrice(order);
    }

    /**
     * @return the total
     */
    public double getTotal() {
        return total;
    }

    /**
     * @param total the total to set
     */
    public void setTotal(double total) {
        this.total = total;
    }

}
