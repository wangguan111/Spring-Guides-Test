
package com.gilbert.test.accessingdataneo4j;

import java.util.Arrays;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AccessingDataNeo4jController {

	private final static Logger log = LoggerFactory.getLogger(AccessingDataNeo4jController.class);

	@Autowired
	PersonRepository personRepository;

	@GetMapping("/neo4j")
	public void neo4j() {
		personRepository.deleteAll();

		Person greg = new Person("Greg");
		Person roy = new Person("Roy");
		Person craig = new Person("Craig");

		List<Person> team = Arrays.asList(greg, roy, craig);
		log.info("Before linking up with Neo4j...");
		team.stream().forEach(person -> log.info("\t" + person.toString()));

		personRepository.save(greg);
		personRepository.save(roy);
		personRepository.save(craig);

		greg = personRepository.findByName(greg.getName());
		greg.worksWith(roy);
		greg.worksWith(craig);
		personRepository.save(greg);

		roy = personRepository.findByName(roy.getName());
		roy.worksWith(craig);
		// We already know that roy works with greg
		personRepository.save(roy);

		// We already know craig works with roy and greg
		log.info("Lookup each person by name...");
		team.stream().forEach(person -> log.info(
				"\t" + personRepository.findByName(person.getName()).toString()));

		List<Person> teammates = personRepository.findByTeammatesName(greg.getName());
		log.info("The following have Greg as a teammate...");
		teammates.stream().forEach(person -> log.info("\t" + person.getName()));
	}
}
