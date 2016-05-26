/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.someone.pizzaservice.web.http;

import com.someone.pizzaservice.domain.pizza.Pizza;
import com.someone.pizzaservice.service.pizza.PizzaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;

/**
 *
 * @author Andrii_Kozak1
 */
public class StringToPizza  implements Converter<String,Pizza>{

    @Autowired
    PizzaService pizzaService;
 //   @Override
    public Pizza convert(String pizzaId) {
        System.out.println("Hello from converter");
        Pizza pizza =new Pizza();
           if (pizzaId!=null&&!pizzaId.isEmpty()){
                Integer id = Integer.valueOf(pizzaId);
                System.out.println("Property editor redorting "+id);
                pizza=pizzaService.getPizzaByID(id);    
            
           }
        return pizza;   
    }
    
}
