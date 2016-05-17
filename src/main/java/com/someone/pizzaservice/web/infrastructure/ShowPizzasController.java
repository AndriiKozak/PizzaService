/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.someone.pizzaservice.web.infrastructure;

import com.someone.pizzaservice.service.pizza.PizzaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author Andrii_Kozak1
 */
@org.springframework.stereotype.Controller
public class ShowPizzasController {
    @Autowired
    PizzaService pizzaService;
    @RequestMapping("/ShowPizzas")
    public String helloWorld(Model model) {
        model.addAttribute("pizzas",pizzaService.getAll() );
        return "ShowPizzas";
    } 
    
}
