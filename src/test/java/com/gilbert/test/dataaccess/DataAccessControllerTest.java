package com.gilbert.test.dataaccess;

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
class DataAccessControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@Test
	public void testForString() throws Exception {

		this.mockMvc.perform(get("/dataAccess/string")).andDo(print()).
				andExpect(status().isOk())
				.andExpect(jsonPath("$").value("Josh"));
	}

	@Test
	public void testForString1() throws Exception {

		this.mockMvc.perform(get("/dataAccess/string1")).andDo(print()).
				andExpect(status().isOk())
				.andExpect(jsonPath("$").value("Josh"));
	}

	@Test
	public void testForObject() throws Exception {

		this.mockMvc.perform(get("/dataAccess/object")).andDo(print()).
				andExpect(status().isOk())
				.andExpect(jsonPath("$.id").value(3))
				.andExpect(jsonPath("$.firstName").value("Josh"))
				.andExpect(jsonPath("$.lastName").value("Bloch"));
	}

	@Test
	public void testForList() throws Exception {

		this.mockMvc.perform(get("/dataAccess/list")).andDo(print()).
				andExpect(status().isOk())
				.andExpect(jsonPath("$..id").value(3))
				.andExpect(jsonPath("$..firstName").value("Josh"))
				.andExpect(jsonPath("$..lastName").value("Bloch"));
	}

	@Test
	public void testForCount() throws Exception {

		this.mockMvc.perform(get("/dataAccess/count")).andDo(print()).
				andExpect(status().isOk())
				.andExpect(jsonPath("$").value(4));
	}
}
