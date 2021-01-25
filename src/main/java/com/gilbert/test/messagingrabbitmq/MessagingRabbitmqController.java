package com.gilbert.test.messagingrabbitmq;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;

@RestController
public class MessagingRabbitmqController {

    private static final Logger log = LoggerFactory.getLogger(MessagingRabbitmqController.class);
    static final String routingKey = "foo.bar.baz";
    static final String message = "Hello from RabbitMQ!";

    //@Autowired
    RabbitTemplate rabbitTemplate;
    //@Autowired
    MessageListener messageListener;

    @GetMapping("/rabbitmq")
    public void rabbitmq() throws Exception {
        log.info("Sending message...");
        rabbitTemplate.convertAndSend(MessagingRabbitmq.topicExchangeName, routingKey, message);
        messageListener.getLatch().await(10000, TimeUnit.MILLISECONDS);
    }
}
