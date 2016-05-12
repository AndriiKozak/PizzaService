/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.someone.pizzaservice.service.pizza;

import com.someone.pizzaservice.domain.pizza.Pizza;

/**
 *
 * @author Andrii_Kozak1
 */
public interface PizzaService {

    Pizza getPizzaByID(Integer id);

    Pizza createPizza(Pizza pizza);
}
