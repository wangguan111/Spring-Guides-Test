package com.gilbert.test.messagingrabbitmq;

import java.util.concurrent.CountDownLatch;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @author gilbertwang
 */
@Component
@Slf4j
public class MessageListener {

	private CountDownLatch latch = new CountDownLatch(1);

	public void receiveMessage(String message) {
		log.info("Received <" + message + ">");
		latch.countDown();
	}

	public CountDownLatch getLatch() {
		return latch;
	}

}
