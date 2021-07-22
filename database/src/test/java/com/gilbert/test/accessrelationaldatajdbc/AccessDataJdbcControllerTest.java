package com.gilbert.test.accessrelationaldatajdbc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
class AccessDataJdbcControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@Test
	public void testQueryForObjectNoParameter() throws Exception {

		this.mockMvc.perform(get("/jdbc/string")).andDo(print()).
				andExpect(status().isOk())
				.andExpect(jsonPath("$").value("Josh"));
	}

	@Test
	public void testQueryForObjectParameter() throws Exception {

		this.mockMvc.perform(get("/jdbc/string1")).andDo(print()).
				andExpect(status().isOk())
				.andExpect(jsonPath("$").value("Josh"));
	}

	@Test
	public void testForObject() throws Exception {

		this.mockMvc.perform(get("/jdbc/object")).andDo(print()).
				andExpect(status().isOk())
				.andExpect(jsonPath("$.id").value(3))
				.andExpect(jsonPath("$.firstName").value("Josh"))
				.andExpect(jsonPath("$.lastName").value("Bloch"));
	}

	@Test
	public void testForList() throws Exception {

		this.mockMvc.perform(get("/jdbc/list")).andDo(print()).
				andExpect(status().isOk())
				.andExpect(jsonPath("$..id").value(3))
				.andExpect(jsonPath("$..firstName").value("Josh"))
				.andExpect(jsonPath("$..lastName").value("Bloch"));
	}

	@Test
	public void testForCount() throws Exception {

		this.mockMvc.perform(get("/jdbc/count")).andDo(print()).
				andExpect(status().isOk())
				.andExpect(jsonPath("$").value(4));
	}
}
