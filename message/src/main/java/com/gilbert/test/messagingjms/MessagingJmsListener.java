package com.gilbert.test.messagingjms;

import lombok.extern.slf4j.Slf4j;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

/**
 * @author gilbertwang
 */
@Component
@Slf4j
public class MessagingJmsListener {

    @JmsListener(destination = "mailbox", containerFactory = "myFactory")
    public void listener(Email email) {
        log.debug("Received <" + email + ">");
    }
}
