package com.gilbert.test.managingtransactions;

import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author gilbertwang
 */
@Component
@Slf4j
public class BookingService {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Transactional
	public void book(String... persons) {
		for (String person : persons) {
			log.info("Booking " + person + " in a seat...");
			jdbcTemplate.update("insert into BOOKINGS(FIRST_NAME) values (?)", person);
		}
	}

	public List<String> findAllBookings() {
		return jdbcTemplate.query("select FIRST_NAME from BOOKINGS",
				(rs, rowNum) -> rs.getString("FIRST_NAME"));
	}

	public void deleteAll(){
		jdbcTemplate.execute("truncate table BOOKINGS");
	}
}
