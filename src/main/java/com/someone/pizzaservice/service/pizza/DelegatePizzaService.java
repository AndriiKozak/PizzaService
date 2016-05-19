/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.someone.pizzaservice.service.pizza;

import com.someone.pizzaservice.domain.pizza.Pizza;
import com.someone.pizzaservice.repository.pizza.PizzaRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Andrii_Kozak1
 */
@Service
@Transactional
public class DelegatePizzaService implements PizzaService {

    PizzaRepository pizzaRepository;

    @Autowired
    public DelegatePizzaService(PizzaRepository pizzaRepository) {
        this.pizzaRepository = pizzaRepository;
    }

    @Override
    public Pizza getPizzaByID(Integer id) {
        return pizzaRepository.getPizzaByID(id);
    }

    @Override
    public Pizza createPizza(Pizza pizza) {
        return pizzaRepository.createPizza(pizza);
    }
    
    public List<Pizza> getAll(){
        return pizzaRepository.getAll();
        
        
    }

    public Pizza updatePizza(Pizza pizza) {
        return pizzaRepository.updatePizza(pizza);
    }

    public void deletePizza(Pizza pizza) {
        pizzaRepository.deletePizza(pizza);
    }
    
}
