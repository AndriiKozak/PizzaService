/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.someone.pizzaservice.domain.order;

import com.someone.pizzaservice.domain.customer.Customer;
import com.someone.pizzaservice.domain.pizza.Pizza;
import com.someone.pizzaservice.infrastructure.Domain;
import java.util.HashSet;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.MapKeyJoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import org.springframework.context.annotation.Scope;

/**
 *
 * @author Andrii_Kozak1
 */
@Domain
@Scope("prototype")
@Entity
@Table(name = "Orders")
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
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long id;
    @Enumerated(EnumType.STRING)
    private OrderState state;
    @OneToOne(cascade = CascadeType.ALL)
    private Customer customer;
//    @ManyToMany(cascade = CascadeType.ALL)
//    @JoinTable(name = "Pizzas_in_order",
//            joinColumns = @JoinColumn(name = "order_id"),
//            inverseJoinColumns = @JoinColumn(name = "pizza_id"))
    @ElementCollection
    @CollectionTable(name="Pizzas_in_order", joinColumns = @JoinColumn(name = "order_id"))
    @MapKeyJoinColumn(name="pizza_id")
    @Column(name="count")
    private Map<Pizza,Integer> pizzaCountMap;

    public Order() {
    }

    public Order(Customer customer, Map<Pizza,Integer> pizzaCountMap) {
        state = OrderState.NEW;
        this.customer = customer;
        this. pizzaCountMap =  pizzaCountMap;
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
    public Map<Pizza,Integer> getPizzaCountMap() {
        return pizzaCountMap;
    }

    /**
     * @param pizzaList the pizzaList to set
     */
    public void setPizzaCountMap(Map<Pizza,Integer> pizzaCountMap) {
        this.pizzaCountMap = pizzaCountMap;
    }

    public String toString() {
        return "Order{"
                + "customer=" + getCustomer()
                + ", pizza=" + pizzaCountMap
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
