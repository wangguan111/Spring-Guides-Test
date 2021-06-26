package com.gilbert.test.caching;

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
class CachingControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@Test
	public void cachingControllerTest() throws Exception {

		this.mockMvc.perform(get("/caching")).andDo(print()).
				andExpect(status().isOk())
				.andExpect(jsonPath("$.isbn").value("isbn-1234"))
				.andExpect(jsonPath("$.title").value("Some book"));
	}
}
