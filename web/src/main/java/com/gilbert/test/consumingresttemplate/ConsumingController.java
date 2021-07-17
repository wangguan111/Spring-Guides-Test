package com.gilbert.test.consumingresttemplate;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * @author gilbertwang
 */
@RestController
public class ConsumingController {

    @Value("${consuming.url}")
    private String url;

    @GetMapping("/consuming")
    public Quote consuming(RestTemplate restTemplate) {
        return restTemplate.getForObject(this.url, Quote.class);
    }
}
