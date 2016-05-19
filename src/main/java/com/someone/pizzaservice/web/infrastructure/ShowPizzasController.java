/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.someone.pizzaservice.web.infrastructure;

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
public class ShowPizzasController {
    @Autowired
    PizzaService pizzaService;
    //@ModelAttribute
    Pizza findPizza(@RequestParam(value="PizzaId",required=false) Integer pizzaId){
        Pizza pizza=new Pizza();
        if (pizzaId!=null) pizza=pizzaService.getPizzaByID(pizzaId);
        return pizza;    
    }
    
    @RequestMapping("/ShowPizzas")
    public String showPizzas(Model model) {
        model.addAttribute("pizzas",pizzaService.getAll() );
        System.out.println("showing pizzas");
        return "ShowPizzas";        
    }
    @RequestMapping(value="/ShowPizzas", method=RequestMethod.POST)
    public String redirectingToEdit(Model model, @RequestParam(value="PizzaId",required=false) Integer pizzaId, @RequestParam(value="action",required=true)String action,  final RedirectAttributes redirectAttributes) {
         Pizza pizza=new Pizza();
        if (pizzaId!=null) pizza=pizzaService.getPizzaByID(pizzaId);
        redirectAttributes.addFlashAttribute(pizza);
        System.out.println("gonna do "+ action);
        System.out.println("gonna editing pizza");
        System.out.println(pizza);
        if (action.equals("delete")){
            pizzaService.deletePizza(pizza);
            return "redirect:ShowPizzas";}
        else
            return "redirect:addNew";        
    }
    
    
    @RequestMapping(value="/addNew")
    public String addNew(Model model){
        return "pizzaEdit";
    }
    @RequestMapping(value="/addNew", method = RequestMethod.POST)
    public String editPizza(@ModelAttribute Pizza pizza){
        System.out.println("editing pizza "+pizza.getId()+" "+pizza.getName());
        if (pizza.getId()==null)
            pizzaService.createPizza(pizza);
        else 
            pizzaService.updatePizza(pizza);
        System.out.println(pizza);
        return "redirect:ShowPizzas";    
    } 
    
}
