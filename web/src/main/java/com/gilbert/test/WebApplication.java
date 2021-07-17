package com.gilbert.test;

import com.gilbert.test.uploadingfiles.storage.StorageProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * @author gilbertwang
 */
@SpringBootApplication
@EnableScheduling
@EnableConfigurationProperties(StorageProperties.class)
@EnableAsync
@EnableCaching
public class WebApplication {

	public static void main(String[] args) {
		SpringApplication.run(WebApplication.class, args);
	}
}
