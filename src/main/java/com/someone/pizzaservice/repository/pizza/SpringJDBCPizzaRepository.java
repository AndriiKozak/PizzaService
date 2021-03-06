/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.someone.pizzaservice.repository.pizza;

import com.someone.pizzaservice.domain.pizza.Pizza;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Andrii_Kozak1
 */
@Transactional
@Repository("SpringJDBCPizzaRepository")
public class SpringJDBCPizzaRepository implements PizzaRepository {

    @PersistenceContext
    EntityManager em;

    @Override

    public Pizza getPizzaByID(Integer id) {
        Pizza pizza = em.find(Pizza.class, id);
        return pizza;
    }

    @Override
    public Pizza createPizza(Pizza pizza) {
        em.persist(pizza);
        return pizza;
    }
    @Override
    public List<Pizza> getAll() {
    return em.createQuery("select p from Pizza p", Pizza.class).getResultList();
 }

    @Override
    public Pizza updatePizza(Pizza pizza) {
       return em.merge(pizza);
    }
    public void deletePizza(Pizza pizza) {
        pizza=em.merge(pizza);
        em.remove(pizza);
    }
    
}
