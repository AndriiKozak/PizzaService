/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.someone.pizzaservice.web.infrastructure;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 *
 * @author Andrii_Kozak1
 */
public class DispatcherServlet extends HttpServlet {

    HandlerMapping handlerMapping;
    ConfigurableApplicationContext repoContext;
    ConfigurableApplicationContext appContext;
    ConfigurableApplicationContext webContext;

    @Override
    public void init() throws ServletException {
        String contextParam = this.getServletContext().getInitParameter("contextConfigLocation");
        String[] contextLocations = contextParam.split(" ");
        String repoContextLocation = contextLocations[0];
        String appContextLocation = contextLocations[1];
        String webContextLocation = this.getInitParameter("contextConfigLocation");
        repoContext = new ClassPathXmlApplicationContext(repoContextLocation);
        appContext = new ClassPathXmlApplicationContext(new String[]{appContextLocation}, repoContext);
        webContext = new ClassPathXmlApplicationContext(new String[]{webContextLocation}, appContext);
        handlerMapping = new SpringUrlHandlerMapping(webContext);
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String url = req.getRequestURI();
        String controllerName = getControllerName(url);
        System.out.println(controllerName);
        Controller controller = handlerMapping.getController(controllerName);
        controller.HandleRequest(req, resp);
    }

    private String getControllerName(String url) {
        return url.substring(url.lastIndexOf('/'));
    }

    @Override
    public void destroy() {
        webContext.close();
        appContext.close();
        repoContext.close();
    }
}
