/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.someone.pizzaservice.repository.pizza;

import com.someone.pizzaservice.domain.pizza.Pizza;
import com.someone.pizzaservice.domain.pizza.PizzaType;
import com.someone.pizzaservice.infrastructure.Benchmark;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Andrii_Kozak1
 */
//@Repository("inMemPizzaRepository")
public class InMemPizzaRepository implements PizzaRepository {

    private int id = 1;
    private List<Pizza> pizzas = new ArrayList<Pizza>();

    //@Benchmark(active = false
    //   @Benchmark
    public Pizza getPizzaByID(Integer id) {
        return pizzas.get(id);
    }

    @PostConstruct
    public void cookPizzas() {
        pizzas.add(new Pizza(1, "Pizza1", 12.4, PizzaType.Meat));
        pizzas.add(new Pizza(2, "Pizza2", 24.4, PizzaType.Vegeterian));
        pizzas.add(new Pizza(3, "Pizza3", 22.5, PizzaType.Sea));
        id = 4;
    }

    @Override
    public Pizza createPizza(Pizza pizza) {
        pizza.setId(id++);
        pizzas.add(pizza);
        return pizza;
    }

    @Override
    public List<Pizza> getAll() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Pizza updatePizza(Pizza pizza) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void deletePizza(Pizza pizza) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
