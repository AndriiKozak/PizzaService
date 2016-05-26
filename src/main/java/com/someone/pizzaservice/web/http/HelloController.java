/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.someone.pizzaservice.web.http;

import java.io.PrintWriter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author Andrii_Kozak1
 */
@org.springframework.stereotype.Controller
public class HelloController implements Controller {

    @RequestMapping("/hello")
    @Override
    public void HandleRequest(HttpServletRequest req, HttpServletResponse resp) {
        try (PrintWriter out = resp.getWriter()) {
            out.println("Hello from Controller");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
