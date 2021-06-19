package com.gilbert.test.multimodule;

import com.gilbert.test.service.MyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MultiModuleController {

	@Autowired
	private MyService myService;

	@GetMapping("/multi")
	public String multiModule() {
		return myService.message();
	}
}
