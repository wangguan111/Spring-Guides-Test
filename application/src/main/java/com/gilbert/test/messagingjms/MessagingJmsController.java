package com.gilbert.test.messagingjms;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author gilbertwang
 */
@RestController
@Slf4j
public class MessagingJmsController {
    @Autowired
    JmsTemplate jmsTemplate;

    @GetMapping("/jms")
    public Email jms() throws Exception {
        // Send a message with a POJO - the template reuse the message converter
        log.debug("Sending an email message.");
        jmsTemplate.convertAndSend("mailbox", new Email("xxx@126.com", "Hello"));
        return new Email("jms@126.com", "Hello");
    }
}
