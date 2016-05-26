/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.someone.pizzaservice.web.http;

import com.someone.pizzaservice.domain.customer.Customer;
import com.someone.pizzaservice.domain.order.Order;
import com.someone.pizzaservice.domain.pizza.Pizza;
import com.someone.pizzaservice.service.order.OrderService;
import com.someone.pizzaservice.service.pizza.PizzaService;
import java.util.HashMap;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

/**
 *
 * @author Andrii_Kozak1
 */
@SessionAttributes({"cart","customer"})
@org.springframework.stereotype.Controller
public class OrderController {
   
    @Autowired
    PizzaService pizzaService;
    @Autowired
    OrderService orderService;
    
    
    
    @RequestMapping("/OrderPizzas")
    public String showPizzas(Model model) {
        model.addAttribute("pizzas",pizzaService.getAll() );
        System.out.println("showing pizzas for ordering");
        return "OrderPizza";
    }
    @RequestMapping("/addToOrder")
    public String addToOrder(Model model,@RequestParam("count") Integer count, @ModelAttribute Pizza pizza){
        if(model.asMap().get("customer")==null) return "redirect:newCustomer";
        System.out.println("ordering "+count+" instances of "+pizza);
        Map<Pizza,Integer> cart=(Map<Pizza,Integer>)model.asMap().get("cart");
        if (cart==null) cart=new HashMap<>();
        Integer oldCount=cart.get(pizza);
        if (oldCount!=null){
            System.out.println("You already ordered "+ oldCount);
            cart.put(pizza,oldCount+count);
        } else {
            System.out.println("its new pizza in order");
            cart.put(pizza, count);
        }
        model.addAttribute("cart", cart);
           
        return "redirect:OrderPizzas";
    }
    @RequestMapping("/placeOrder") 
    public String placeOrder(Model model, @ModelAttribute Customer customer, @ModelAttribute("cart") Map<Pizza,Integer> cart ){
        Order order=orderService.placeNewOrder(customer, cart);
        model.addAttribute(order);
        return "placeOrder";
    }
}