package com.gilbert.test;

import com.gilbert.test.uploadingfiles.storage.StorageProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.data.neo4j.repository.config.EnableNeo4jRepositories;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * @author gilbertwang
 */
@SpringBootApplication
@EnableScheduling
@EnableConfigurationProperties(StorageProperties.class)
@EnableNeo4jRepositories
@EnableJms
@EnableAsync
public class TestApplication {

	public static void main(String[] args) {
		SpringApplication.run(TestApplication.class, args);
	}
}
