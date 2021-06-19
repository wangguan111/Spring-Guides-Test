package com.gilbert.test.messagingredis;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author gilbertwang
 */
@RestController
public class MessagingRedisController {

    private static final Logger LOGGER = LoggerFactory.getLogger(MessagingRedis.class);

    @Autowired
    StringRedisTemplate template;

    @Autowired
    Receiver receiver;

    @GetMapping("/redis")
    public String redis() {

        if (receiver.getCount() == 0) {
            LOGGER.info("Sending message...");
            template.convertAndSend("chat", "Hello from Redis!");
        }
        return "Hello from Redis!";
    }
}
