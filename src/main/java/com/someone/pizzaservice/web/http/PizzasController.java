/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.someone.pizzaservice.web.http;

import com.someone.pizzaservice.domain.pizza.Pizza;
import com.someone.pizzaservice.service.pizza.PizzaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 *
 * @author Andrii_Kozak1
 */
@org.springframework.stereotype.Controller
public class PizzasController {
    @Autowired
    PizzaService pizzaService;
    
    
    @RequestMapping("/ShowPizzas")
    public String showPizzas(Model model) {
        model.addAttribute("pizzas",pizzaService.getAll() );
        System.out.println("showing pizzas");
        return "ShowPizzas";        
    }
    @RequestMapping(value="/delete", method=RequestMethod.POST)
    public String delete(Model model) {
        Pizza pizza = (Pizza)model.asMap().get("pizza");
        pizzaService.deletePizza(pizza);
        return "redirect:ShowPizzas";
    }
    
    
    @RequestMapping(value="/addNew")
    public String addNew(Model model){
        return "pizzaEdit";
    }
    @RequestMapping(value="/save", method = RequestMethod.POST)
    public String editPizza(@ModelAttribute Pizza pizza){
        System.out.println("editing pizza "+pizza);
        if (pizza.getId()==null)
            pizzaService.createPizza(pizza);
        else 
            pizzaService.updatePizza(pizza);
        System.out.println(pizza);
        return "redirect:ShowPizzas";    
    } 
    
}
