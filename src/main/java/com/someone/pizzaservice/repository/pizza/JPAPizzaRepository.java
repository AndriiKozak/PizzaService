/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.someone.pizzaservice.repository.pizza;

import com.someone.pizzaservice.domain.pizza.Pizza;
import com.someone.pizzaservice.domain.pizza.PizzaType;
import javax.persistence.EntityManager;

/**
 *
 * @author Andrii_Kozak1
 */
public class JPAPizzaRepository implements PizzaRepository {

    EntityManager em;

    public JPAPizzaRepository(EntityManager em) {
        this.em = em;
    }

    @Override
    public Pizza getPizzaByID(Integer id) {
        Pizza pizza = em.find(Pizza.class, id);
        return pizza;
    }

    public void cookPizzas() {
        em.getTransaction().begin();
        em.persist(new Pizza("Pizza1", 12.4, PizzaType.Meat));
        em.persist(new Pizza("Pizza2", 24.4, PizzaType.Vegeterian));
        em.persist(new Pizza("Pizza3", 22.5, PizzaType.Sea));
        em.getTransaction().commit();
    }

}
