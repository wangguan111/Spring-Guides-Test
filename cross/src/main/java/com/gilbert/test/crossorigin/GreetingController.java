package com.gilbert.test.crossorigin;

import java.util.concurrent.atomic.AtomicLong;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author gilbertwang
 */
@RestController
@Slf4j
public class GreetingController {

	private static final String TEMPLATE = "Hello, %s!";
	private final AtomicLong counter = new AtomicLong();

	@CrossOrigin(origins = "http://localhost:8087")
	@GetMapping("/greeting")
	public Greeting greeting(@RequestParam(required = false, defaultValue = "World") String name) {
		log.debug("==== get greeting ====");
		return new Greeting(counter.incrementAndGet(), String.format(TEMPLATE, name));
	}

	@GetMapping("/greeting-config")
	public Greeting greetingWithJavaconfig(@RequestParam(required = false, defaultValue = "World") String name) {
		log.debug("==== in greeting ====");
		return new Greeting(counter.incrementAndGet(), String.format(TEMPLATE, name));
	}

}
