package com.gilbert.test.greeting;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author gilbertwang
 */
@Controller
public class WebLayerController {

	@RequestMapping("/")
	public @ResponseBody String greeting() {
		return "Hello, World";
	}
}
