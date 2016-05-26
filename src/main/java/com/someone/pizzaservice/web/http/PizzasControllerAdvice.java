/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.someone.pizzaservice.web.http;

import com.someone.pizzaservice.domain.pizza.Pizza;
import com.someone.pizzaservice.service.pizza.PizzaService;
import java.beans.PropertyEditorSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author Andrii_Kozak1
 */
@ControllerAdvice
public class PizzasControllerAdvice {
    @Autowired
        PizzaService pizzaService;
    //@ModelAttribute("pizza")
    Pizza dirtectFindPizza(@RequestParam(value="PizzaId",required=false) Integer pizzaId){
        
    Pizza pizza =new Pizza();
        if (pizzaId!=null){
               
                     
                pizza=pizzaService.getPizzaByID(pizzaId);  
        }
        System.out.println("find" + pizza);
        return pizza;            
    }
    @ModelAttribute("pizza")
    Pizza binderFindPizza(@RequestParam(value="PizzaId",required=false) Pizza pizza){
        System.out.println("find" + pizza);
        // Here things gone tricky. If we do not have PizzaId, property editor DO NOT even bothers, and 
        // pizza will be null, not new Pizza(). And in this case spring will NOT create a pizza object from
        //jps form. If we manually create pizza here, however, it will fill needed values into settors. 
        if (pizza==null) pizza=new Pizza(); 
        return pizza;            
    }
    
    
    
    @InitBinder
    void initBinder(WebDataBinder binder){
        binder.registerCustomEditor(Pizza.class, new PizzaPropertyEditor());
    }
    class PizzaPropertyEditor extends PropertyEditorSupport{
        @Override
        public void setAsText(String pizzaId) throws IllegalArgumentException {
            System.out.println("Property editor redorting "+pizzaId);
           Pizza pizza =new Pizza();
           if (pizzaId!=null&&!pizzaId.isEmpty()){
                Integer id = Integer.valueOf(pizzaId);
                            System.out.println("Property editor redorting "+id);
                pizza=pizzaService.getPizzaByID(id);    
            
           }
            System.out.println(pizza);   
        setValue(pizza);   
        }
        
    } 
}
