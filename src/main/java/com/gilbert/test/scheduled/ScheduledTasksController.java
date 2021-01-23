package com.gilbert.test.scheduled;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;
import java.util.Date;

@RestController
public class ScheduledTasksController {

    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");

    @Scheduled(fixedRate = 5000)
    public void reportCurrentTime() {
        dateFormat.format(new Date());
    }

    @GetMapping("/scheduledTasks")
    public String scheduledTasks() {
        return dateFormat.format(new Date());
    }
}
