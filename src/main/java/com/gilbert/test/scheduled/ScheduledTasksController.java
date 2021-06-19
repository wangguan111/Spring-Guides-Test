package com.gilbert.test.scheduled;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * @author gilbertwang
 */
@RestController
public class ScheduledTasksController {

    @GetMapping("/scheduledTasks")
    public String scheduledTasks() {
        LocalDateTime localDateTime = LocalDateTime.now();
        DateTimeFormatter dataFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");
        return localDateTime.format(dataFormatter);
    }
}
