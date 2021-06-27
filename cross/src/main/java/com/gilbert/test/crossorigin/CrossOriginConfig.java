package com.gilbert.test.crossorigin;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author gilbertwang
 */
@Component
public class CrossOriginConfig {

    @Bean
    public WebMvcConfigurer corConfig() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/greeting-config").
                        allowedOrigins("http://localhost:8087");
            }
        };
    }
}