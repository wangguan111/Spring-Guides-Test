package com.gilbert.test.scheduled;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@RestController
public class ScheduledTasksController {

    @Scheduled(fixedRate = 5000)
    public void reportCurrentTime() {
        LocalDateTime localDateTime = LocalDateTime.now();
        DateTimeFormatter dataFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");
        localDateTime.format(dataFormatter);
    }

    @GetMapping("/scheduledTasks")
    public String scheduledTasks() {
        LocalDateTime localDateTime = LocalDateTime.now();
        DateTimeFormatter dataFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");
        return localDateTime.format(dataFormatter);
    }
}
