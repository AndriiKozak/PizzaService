/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.someone.pizzaservice.repository.pizza;

import com.someone.pizzaservice.domain.pizza.Pizza;
import com.someone.pizzaservice.domain.pizza.PizzaType;
import com.someone.pizzaservice.repository.EMPlaceholder;
import java.util.List;
import javax.persistence.EntityManager;

/**
 *
 * @author Andrii_Kozak1
 */
public class JPAPizzaRepository implements PizzaRepository {

    EMPlaceholder emPlaceholder;

    public JPAPizzaRepository(EMPlaceholder emPlaceholder) {
        this.emPlaceholder = emPlaceholder;
    }

    @Override
    public Pizza getPizzaByID(Integer id) {
        Pizza pizza = emPlaceholder.em.find(Pizza.class, id);
        return pizza;
    }

    public void cookPizzas() {
        emPlaceholder.em.getTransaction().begin();
        emPlaceholder.em.persist(new Pizza("Pizza1", 12.4, PizzaType.Meat));
        emPlaceholder.em.persist(new Pizza("Pizza2", 24.4, PizzaType.Vegeterian));
        emPlaceholder.em.persist(new Pizza("Pizza3", 22.5, PizzaType.Sea));
        emPlaceholder.em.getTransaction().commit();
    }

    @Override
    public Pizza createPizza(Pizza pizza) {
        emPlaceholder.em.getTransaction().begin();
        emPlaceholder.em.persist(pizza);
        emPlaceholder.em.getTransaction().commit();
        return pizza;
    }

    @Override
    public List<Pizza> getAll() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
