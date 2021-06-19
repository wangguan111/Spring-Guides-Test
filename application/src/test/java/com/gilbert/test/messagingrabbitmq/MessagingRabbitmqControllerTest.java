package com.gilbert.test.messagingrabbitmq;

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
class MessagingRabbitmqControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@Test
	public void rabbit() throws Exception {

		this.mockMvc.perform(get("/rabbitmq")).andDo(print()).
				andExpect(status().isOk())
				.andExpect(jsonPath("$").value("Hello from RabbitMQ!"));
	}

}
