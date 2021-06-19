package com.gilbert.test;

import com.gilbert.test.scheduled.ScheduledTasks;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.client.RestTemplate;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class TestApplicationTests {

    @Autowired
    private ScheduledTasks tasks;

    @Autowired
    private RestTemplate restTemplate;

    @Test
    public void contextLoads() {
        // Basic integration test that shows the context starts up properly
        assertThat(tasks).isNotNull();
        assertThat(restTemplate).isNotNull();
    }
}
