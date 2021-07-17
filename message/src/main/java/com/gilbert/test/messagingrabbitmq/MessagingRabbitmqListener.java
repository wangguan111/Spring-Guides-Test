package com.gilbert.test.messagingrabbitmq;

import java.util.concurrent.CountDownLatch;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @author gilbertwang
 */
@Component
@Slf4j
public class MessagingRabbitmqListener {

	private CountDownLatch latch = new CountDownLatch(1);

	public CountDownLatch getLatch() {
		return latch;
	}

	public void listener(String message) {
		log.debug("Received <" + message + ">");
		latch.countDown();
	}
}
