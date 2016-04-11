/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.someone.pizzaservice.domain.order;

import com.someone.pizzaservice.domain.customer.Customer;
import com.someone.pizzaservice.domain.pizza.Pizza;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 *
 * @author Andrii_Kozak1
 */
@Component("order")
@Scope("prototype")
public class Order {

    static final Set<Transition> ALLOWED_TRANSITIONS = new HashSet<>();

    {
        ALLOWED_TRANSITIONS.add(new Transition(OrderState.NEW, OrderState.NEW));
        ALLOWED_TRANSITIONS.add(new Transition(OrderState.IN_PROGRESS, OrderState.IN_PROGRESS));
        ALLOWED_TRANSITIONS.add(new Transition(OrderState.DONE, OrderState.DONE));
        ALLOWED_TRANSITIONS.add(new Transition(OrderState.CANCELLED, OrderState.CANCELLED));
        ALLOWED_TRANSITIONS.add(new Transition(OrderState.NEW, OrderState.CANCELLED));
        ALLOWED_TRANSITIONS.add(new Transition(OrderState.IN_PROGRESS, OrderState.CANCELLED));
        ALLOWED_TRANSITIONS.add(new Transition(OrderState.NEW, OrderState.IN_PROGRESS));
        ALLOWED_TRANSITIONS.add(new Transition(OrderState.IN_PROGRESS, OrderState.DONE));
    }
    private static int sId = 0;
    private long id;
    private OrderState state;
    private Customer customer;
    private List<Pizza> pizzaList;

    public Order() {
    }

    public Order(Customer customer, List<Pizza> pizzaList) {
        id = sId++;
        state = OrderState.NEW;
        this.customer = customer;
        this.pizzaList = pizzaList;
    }

    public Double calculateTotalCost() {
        return customer.getDCard().getDiscountedPrice(this);
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
     * @return the pizzaList
     */
    public List<Pizza> getPizzaList() {
        return pizzaList;
    }

    /**
     * @param pizzaList the pizzaList to set
     */
    public void setPizzaList(List<Pizza> pizzaList) {
        this.pizzaList = pizzaList;
    }

    public String toString() {
        return "Order{"
                + "customer=" + getCustomer()
                + ", pizza=" + pizzaList
                + ", state=" + getState()
                + '}';
    }

    /**
     * @return the state
     */
    public OrderState getState() {
        return state;
    }

    /**
     * @param state the state to set
     */
    public void setState(OrderState state) {
        if (ALLOWED_TRANSITIONS.contains(new Transition(this.state, state))) {
            this.state = state;
        } else {
            throw new RuntimeException("Not allowed order state change");
        }
    }

    /**
     * @return the dCard
     */
    /**
     * @param dCard the dCard to set
     */
    /**
     * @return the customer
     */
    public Customer getCustomer() {
        return customer;
    }

    /**
     * @param customer the customer to set
     */
    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    private static class Transition {

        OrderState from;
        OrderState to;

        Transition(OrderState from, OrderState to) {
            this.from = from;
            this.to = to;
        }

        @Override
        public int hashCode() {
            int hash = 3;
            hash = 67 * hash + Objects.hashCode(this.from);
            hash = 67 * hash + Objects.hashCode(this.to);
            return hash;
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null) {
                return false;
            }
            if (getClass() != obj.getClass()) {
                return false;
            }
            final Transition other = (Transition) obj;
            if (this.from != other.from) {
                return false;
            }
            if (this.to != other.to) {
                return false;
            }
            return true;
        }

    }
}
