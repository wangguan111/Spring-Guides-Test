package com.gilbert.test.accessingdataneo4j;

import java.util.List;
import org.springframework.data.neo4j.repository.Neo4jRepository;

/**
 * @author gilbertwang
 */
public interface PersonRepository extends Neo4jRepository<Person, Long> {

	Person findByName(String name);
	List<Person> findByTeammatesName(String name);
}
