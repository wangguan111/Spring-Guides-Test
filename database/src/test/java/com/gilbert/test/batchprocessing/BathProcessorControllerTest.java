package com.gilbert.test.batchprocessing;

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
class BathProcessorControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@Test
	public void testQueryDefaultParameter() throws Exception {

		this.mockMvc.perform(get("/bath")).andDo(print()).
				andExpect(status().isOk())
				.andExpect(jsonPath("$.firstName").value("JUSTIN"));
	}

	@Test
	public void testQueryInputParameter() throws Exception {

		this.mockMvc.perform(get("/bath?name=JILL")).andDo(print()).
				andExpect(status().isOk())
				.andExpect(jsonPath("$.firstName").value("JILL"));
	}
}
