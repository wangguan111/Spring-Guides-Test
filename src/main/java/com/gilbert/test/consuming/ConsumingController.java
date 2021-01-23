package com.gilbert.test.consuming;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class ConsumingController {

    @Autowired
    RestTemplate restTemplate;

    @GetMapping("/consuming")
    public Quote consuming() {
//        return restTemplate.getForObject(
//                    "https://gturnquist-quoters.cfapps.io/api/random", Quote.class);
        return null;
    }
}
