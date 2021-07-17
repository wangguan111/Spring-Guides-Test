package com.gilbert.test.scheduled;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * @author gilbertwang
 */
@Component
public class ScheduledTasks {

	@Scheduled(fixedRate = 1000)
	public void reportCurrentTime() {
		LocalDateTime localDateTime = LocalDateTime.now();
		DateTimeFormatter dataFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");
		localDateTime.format(dataFormatter);
	}
}
