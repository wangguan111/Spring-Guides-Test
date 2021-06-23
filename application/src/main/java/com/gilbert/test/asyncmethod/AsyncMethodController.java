package com.gilbert.test.asyncmethod;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;

@RestController
@Slf4j
public class AsyncMethodController {

	@Bean
	public Executor taskExecutor() {
		ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
		executor.setCorePoolSize(2);
		executor.setMaxPoolSize(2);
		executor.setQueueCapacity(500);
		executor.setThreadNamePrefix("GithubLookup-");
		executor.initialize();
		return executor;
	}

	@Autowired
	private GitHubLookupService gitHubLookupService;

	@GetMapping("/async")
	public User asyncMethod() throws Exception {
		// Start the clock
		long start = System.currentTimeMillis();

		// Kick of multiple, asynchronous lookups
		CompletableFuture<User> page1 = gitHubLookupService.findUser("PivotalSoftware");
		CompletableFuture<User> page2 = gitHubLookupService.findUser("CloudFoundry");
		CompletableFuture<User> page3 = gitHubLookupService.findUser("Spring-Projects");

		// Wait until they are all done
		CompletableFuture.allOf(page1,page2,page3).join();

		// Print results, including elapsed time
		log.info("Elapsed time: " + (System.currentTimeMillis() - start));
		log.info("--> " + page1.get());
		log.info("--> " + page2.get());
		log.info("--> " + page3.get());
		return page1.get();
	}
}
