package com.gilbert.test.managingtransactions;

import static org.hamcrest.Matchers.equalTo;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
class BookingControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@Test
	public void testBookAll() throws Exception {

		this.mockMvc.perform(get("/book/all")).andDo(print()).
				andExpect(status().isOk())
				.andExpect(jsonPath("$").value("Carol"));
	}

	@Test
	public void testBookBig() throws Exception {

		this.mockMvc.perform(get("/book/big")).andDo(print()).
				andExpect(status().isOk())
				.andExpect(content().string(equalTo("[]")));

	}

	@Test
	public void testBookNull() throws Exception {

		this.mockMvc.perform(get("/book/null")).andDo(print()).
				andExpect(status().isOk())
				.andExpect(content().string(equalTo("[]")));
	}
}
