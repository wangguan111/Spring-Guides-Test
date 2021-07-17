package com.gilbert.test.asyncmethod;

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
public class AsyncMethodControllerTests {

	@Autowired
	private MockMvc mockMvc;

	@Test
	public void findByFirstName() throws Exception {
		this.mockMvc.perform(get("/async")).andDo(print()).
				andExpect(status().isOk())
				.andExpect(jsonPath("$.name").value("Pivotal Software, Inc."))
				.andExpect(jsonPath("$.blog").value("http://pivotal.io"));
	}
}
