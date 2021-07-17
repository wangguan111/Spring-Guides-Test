package com.gilbert.test.messagingredis;

import java.util.concurrent.atomic.AtomicInteger;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @author gilbertwang
 */
@Component
@Slf4j
public class MessagingRedisListener {

    private AtomicInteger counter = new AtomicInteger();

    public int getCount() {
        return counter.get();
    }

    public void listener(String message) {
        log.debug("Received <" + message + ">");
        counter.incrementAndGet();
    }
}
