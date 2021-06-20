package com.gilbert.test.batchprocessing;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

/**
 * @author gilbertwang
 */
@RestController
public class BathProcessorController {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @GetMapping("/bath")
    public Person bath(
            @RequestParam(value="name", required=false, defaultValue="JUSTIN")String name)
    {
        String sql = "SELECT first_name, last_name FROM people";
        List<Person> persons= jdbcTemplate.query(sql,
                (rs, row) -> new Person(
                        rs.getString(1),
                        rs.getString(2))
        );
        return persons.stream().filter(p -> name.equals(p.getFirstName())).findFirst().get();
    }
}
