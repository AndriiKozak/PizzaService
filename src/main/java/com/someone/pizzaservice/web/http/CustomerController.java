/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.someone.pizzaservice.web.http;

import com.someone.pizzaservice.domain.customer.Address;
import com.someone.pizzaservice.domain.customer.Customer;
import java.util.ArrayList;
import java.util.List;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

/**
 *
 * @author Andrii_Kozak1
 */
@org.springframework.stereotype.Controller
@SessionAttributes("customer")
public class CustomerController {
    
    @RequestMapping("/newCustomer")
    public String newCustomer(){
        return "newCustomer";
    }
    @RequestMapping("/addNewCustomer")
    public String addNewCustomer(@RequestParam("name")String name, @RequestParam("address") String address, Model model){
        List<Address> addresses=new ArrayList<Address>();
        addresses.add(new Address(address));
        Customer customer = new Customer(name,addresses);
        model.addAttribute(customer);
        return "redirect:OrderPizzas";
    }
}
