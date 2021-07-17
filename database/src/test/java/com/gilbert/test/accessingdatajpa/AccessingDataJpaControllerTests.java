package com.gilbert.test.accessingdatajpa;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

@AutoConfigureMockMvc
@SpringBootTest
public class AccessingDataJpaControllerTests {

	@Autowired
	private MockMvc mockMvc;

	@Test
	public void findByIdTest() throws Exception {
		this.mockMvc.perform(get("/jpa/id")).andDo(print()).
				andExpect(status().isOk())
				.andExpect(jsonPath("$.id").value("1"))
				.andExpect(jsonPath("$.firstName").value("Jack"))
				.andExpect(jsonPath("$.lastName").value("Bauer"));
	}

	@Test
	public void findByLastNameTest() throws Exception {
		this.mockMvc.perform(get("/jpa/name")).andDo(print()).
				andExpect(status().isOk())
				.andExpect(jsonPath("$.id").value("1"))
				.andExpect(jsonPath("$.firstName").value("Jack"))
				.andExpect(jsonPath("$.lastName").value("Bauer"));
	}
}
