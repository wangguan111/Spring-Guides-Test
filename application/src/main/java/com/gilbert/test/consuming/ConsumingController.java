package com.gilbert.test.consuming;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * @author gilbertwang
 */
@RestController
public class ConsumingController {

    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder builder) {
        return builder.build();
    }

    @Value("${consuming.url}")
    private String url;

    @GetMapping("/consuming")
    public Quote consuming(RestTemplate restTemplate) {
        return restTemplate.getForObject(this.url, Quote.class);
    }
}
