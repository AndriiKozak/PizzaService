/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.someone.pizzaservice.web.infrastructure;

import java.io.PrintWriter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Andrii_Kozak1
 */
public class HelloController implements Controller {

    @Override
    public void HandleRequest(HttpServletRequest req, HttpServletResponse resp) {
        try (PrintWriter out = resp.getWriter()) {
            out.println("Hello from Controller");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
