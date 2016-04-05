/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.someone.pizzaservice.repository.pizza;

import com.someone.pizzaservice.domain.pizza.Pizza;
import com.someone.pizzaservice.domain.pizza.PizzaType;
import com.someone.pizzaservice.infrastructure.Benchmark;
import com.someone.pizzaservice.infrastructure.PostConstruction;
import java.util.ArrayList;
import java.util.List;


/**
 *
 * @author Andrii_Kozak1
 */
public class InMemPizzaRepository implements PizzaRepository {

    private List<Pizza> pizzas = new ArrayList<>();

    
    @Benchmark(active=false)    
    public Pizza getPizzaByID(Integer id) {
        return pizzas.get(id);
    }
    @PostConstruction
    public void cookPizzas(){
      pizzas.add(new Pizza("Pizza1", 12.4, PizzaType.Meat));
      pizzas.add(new Pizza("Pizza2", 24.4, PizzaType.Vegeterian));
      pizzas.add(new Pizza("Pizza3", 22.5, PizzaType.Sea));
    }
}
