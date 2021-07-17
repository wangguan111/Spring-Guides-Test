package com.gilbert.test;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.neo4j.repository.config.EnableNeo4jRepositories;

/**
 * @author gilbertwang
 */
@SpringBootApplication
@EnableNeo4jRepositories
public class DataBaseApplication {

	public static void main(String[] args) {
		SpringApplication.run(DataBaseApplication.class, args);
	}
}
