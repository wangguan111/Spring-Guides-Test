package com.gilbert.test.batchprocessing;

import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.item.ItemProcessor;

/**
 * @author gilbertwang
 */
@Slf4j
public class PersonItemProcessor implements ItemProcessor<Person, Person> {

	//7.
	@Override
	public Person process(final Person person) throws Exception {
		final String firstName = person.getFirstName().toUpperCase();
		final String lastName = person.getLastName().toUpperCase();

		final Person transformedPerson = new Person(firstName, lastName);
		log.debug("Converting (" + person + ") into (" + transformedPerson + ")");
		return transformedPerson;
	}
}
