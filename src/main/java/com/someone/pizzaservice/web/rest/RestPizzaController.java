/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.someone.pizzaservice.web.rest;

import com.someone.pizzaservice.domain.pizza.Pizza;
import com.someone.pizzaservice.service.pizza.PizzaService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Andrii_Kozak1
 */
@RestController
public class RestPizzaController {
    @Autowired    
    PizzaService pizzaService;
    
    @RequestMapping(value="pizza",method=RequestMethod.GET)
    public List<Pizza> view (){
        return pizzaService.getAll();
    }
    @RequestMapping(value="pizza/{id}",method=RequestMethod.GET)
    public ResponseEntity<Pizza> find(@PathVariable Integer id){
        Pizza pizza = pizzaService.getPizzaByID(id);
        if (pizza==null) return new ResponseEntity(HttpStatus.NOT_FOUND);
                else return new ResponseEntity(pizza,HttpStatus.FOUND);        
    }
    
   @RequestMapping(value="pizza",method=RequestMethod.POST)
    public ResponseEntity<Pizza> save(@RequestBody Pizza pizza){
        System.out.println(pizza);
        pizza=pizzaService.createPizza(pizza);
        return new ResponseEntity(pizza,HttpStatus.CREATED);
    }
    
}
