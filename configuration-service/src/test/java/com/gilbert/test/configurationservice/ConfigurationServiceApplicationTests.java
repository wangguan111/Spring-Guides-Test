package com.gilbert.test.configurationservice;

import static org.assertj.core.api.BDDAssertions.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ConfigurationServiceApplicationTests {

	@LocalServerPort
	private int port;

	@Autowired
	private TestRestTemplate testRestTemplate;

	@Test
	public void shouldStartConfigurationServer() {
		ResponseEntity<String> entity = this.testRestTemplate.getForEntity(
				"http://localhost:" + this.port + "/config/spring-cloud.properties", String.class);
		then(entity.getStatusCode()).isEqualTo(HttpStatus.OK);
		then(entity.getBody()).isEqualTo("message: Hello MM");
	}
}
