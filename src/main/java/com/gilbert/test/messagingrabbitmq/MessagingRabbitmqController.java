package com.gilbert.test.messagingrabbitmq;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.concurrent.TimeUnit;

/**
 * @author gilbertwang
 */
@RestController
@Slf4j
public class MessagingRabbitmqController {

    static final String ROUTING_KEY = "foo.bar.baz";
    static final String MESSAGE = "Hello from RabbitMQ!";

    @Autowired
    RabbitTemplate rabbitTemplate;
    @Autowired
    MessageListener messageListener;

    @GetMapping("/rabbitmq")
    public String rabbitmq() throws Exception {
        log.info("Sending message...");
        rabbitTemplate.convertAndSend(MessagingRabbitmq.TOPIC_EXCHANGE_NAME, ROUTING_KEY, MESSAGE);
        messageListener.getLatch().await(10000, TimeUnit.MILLISECONDS);
        return MESSAGE;
    }
}
