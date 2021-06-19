package com.gilbert.test.greeting;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Map;
import static org.assertj.core.api.BDDAssertions.then;

@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource(properties = {"management.port=0"})
class GreetingControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@LocalServerPort
	private int port;

	@Value("${local.management.port}")
	private int mgt;

	@Autowired
	private TestRestTemplate testRestTemplate;

	@Test
	public void noParamGreetingShouldReturnDefaultMessage() throws Exception {

		this.mockMvc.perform(get("/greeting")).andDo(print()).
				andExpect(status().isOk())
				.andExpect(jsonPath("$.content").value("Hello, World!"));
	}

	@Test
	public void paramGreetingShouldReturnTailoredMessage() throws Exception {

		this.mockMvc.perform(get("/greeting").param("name", "Spring Community"))
				.andDo(print()).
				andExpect(status().isOk())
				.andExpect(jsonPath("$.content").value("Hello, Spring Community!"));
	}

	@Test
	public void shouldReturn200WhenSendingRequestToController() throws Exception {
		@SuppressWarnings("rawtypes")
		ResponseEntity<Map> entity = this.testRestTemplate.getForEntity(
				"http://localhost:" + this.port + "/greeting", Map.class);
		then(entity.getStatusCode()).isEqualTo(HttpStatus.OK);
		assertThat(entity.getBody().get("content").equals("Hello, World!")).isEqualTo(true);
	}

	@Test
	public void shouldReturn200WhenSendingRequestToManagementEndpoint() throws Exception {
		@SuppressWarnings("rawtypes")
		ResponseEntity<Map> entity = this.testRestTemplate.getForEntity(
				"http://localhost:" + this.mgt + "/actuator/info", Map.class);
		then(entity.getStatusCode()).isEqualTo(HttpStatus.OK);
	}
}
