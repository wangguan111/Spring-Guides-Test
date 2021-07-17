package com.gilbert.test.crossorigin;

import java.net.URI;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class GreetingIntegrationTests {

	@Autowired
	private TestRestTemplate restTemplate;

	private static final String URL = "http://localhost:8082";

	@Test
	public void corWithAnnotation() throws Exception {
		ResponseEntity<Greeting> entity = this.restTemplate.exchange(
				RequestEntity.get(uri("/greeting")).header(HttpHeaders.ORIGIN, URL).build(),
				Greeting.class);
		assertEquals(HttpStatus.OK, entity.getStatusCode());
		Greeting greeting = entity.getBody();
		assertEquals("Hello, World!", greeting.getContent());
	}

	@Test
	public void corWithConfig() {
		ResponseEntity<Greeting> entity = this.restTemplate.exchange(RequestEntity.get(uri("/greeting-config"))
				.header(HttpHeaders.ORIGIN, URL).build(), Greeting.class);
		assertEquals(HttpStatus.OK, entity.getStatusCode());
		Greeting greeting = entity.getBody();
		assertEquals("Hello, World!", greeting.getContent());
	}

	private URI uri(String path) {
		return restTemplate.getRestTemplate().getUriTemplateHandler().expand(path);
	}
}
