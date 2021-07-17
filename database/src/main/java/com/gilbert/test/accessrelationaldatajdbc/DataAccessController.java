package com.gilbert.test.accessrelationaldatajdbc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

/**
 * @author gilbertwang
 */
@RestController
public class DataAccessController {

    @Autowired
    JdbcTemplate jdbcTemplate;

    @GetMapping("/dataAccess/string")
    public String queryForObjectNoParameter() {
        String sql = "SELECT first_name FROM customers WHERE last_name = 'Bloch'";
        return jdbcTemplate.queryForObject(sql, String.class);
    }

    @GetMapping("/dataAccess/string1")
    public String queryForObjectParameter() {
        String sql = "SELECT first_name FROM customers WHERE last_name = ?";
        return jdbcTemplate.queryForObject(sql, String.class, "Bloch");
    }

    String sql = "SELECT id, first_name, last_name FROM customers WHERE last_name = 'Bloch'";

    @GetMapping("/dataAccess/object")
    public Customer object() {
        return jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<>(Customer.class));
    }

    @GetMapping("/dataAccess/list")
    public List<Customer> list() {
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Customer.class));
    }

    @GetMapping("/dataAccess/count")
    public int count() {
        String sql = "SELECT COUNT(*) FROM customers";
        return jdbcTemplate.queryForObject(sql, Integer.class);
    }
}
