package com.gilbert.test.asyncmethod;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import java.util.concurrent.CompletableFuture;

/**
 * @author gilbertwang
 */
@Service
@Slf4j
public class GitHubLookupService {

	@Autowired
	private RestTemplate restTemplate;

	@Async
	public CompletableFuture<User> findUser(String user) throws InterruptedException {
		log.info("Looking up " + user);
		String url = String.format("https://api.github.com/users/%s", user);
		User results = restTemplate.getForObject(url, User.class);
		// Artificial delay of 1s for demonstration purposes
		Thread.sleep(1000L);
		return CompletableFuture.completedFuture(results);
	}
}
