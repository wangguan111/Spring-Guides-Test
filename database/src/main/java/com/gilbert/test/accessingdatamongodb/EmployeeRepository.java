package com.gilbert.test.accessingdatamongodb;

import java.util.List;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * @author gilbertwang
 */
public interface EmployeeRepository extends MongoRepository<Employee, String> {

	List<Employee> findByFirstName(String firstName);
	List<Employee> findByLastName(String lastName);
}
