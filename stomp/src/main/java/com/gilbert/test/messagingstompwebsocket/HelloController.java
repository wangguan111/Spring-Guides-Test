package com.gilbert.test.messagingstompwebsocket;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.util.HtmlUtils;

/**
 * @author gilbertwang
 */
@Controller
public class HelloController {

	@MessageMapping("/hello")
	@SendTo("/topic/greetings")
	public Hello greeting(HelloMessage message) throws Exception {
		// simulated delay
		Thread.sleep(1000);
		return new Hello("Hello, " + HtmlUtils.htmlEscape(message.getName()) + "!");
	}
}
