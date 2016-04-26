/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.someone.pizzaservice.repository.pizza;

import com.someone.pizzaservice.domain.pizza.Pizza;
import com.someone.pizzaservice.domain.pizza.PizzaType;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

/**
 *
 * @author akozak
 */
@Repository("PizzaRepository")
public class JBDCPizzaRepository implements PizzaRepository {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setDataSource(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public Pizza getPizzaByID(Integer id) {
        final String stmt = "select id, pizza_name, price, pizza_type from pizzaservice.pizzas where id=?;";
        return jdbcTemplate.queryForObject(stmt, new PizzaMapper(), id);
    }

    private static final class PizzaMapper implements RowMapper<Pizza> {

        public Pizza mapRow(ResultSet rs, int rowNum) throws SQLException {
            int id = rs.getInt("id");
            String name = rs.getString("pizza_name");
            double price = rs.getDouble("price");
            PizzaType pizzaType = PizzaType.valueOf(rs.getString("pizza_type"));
            return new Pizza(id, name, price, pizzaType);
        }
    }

}
