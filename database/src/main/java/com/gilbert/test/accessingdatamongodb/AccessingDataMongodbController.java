package com.gilbert.test.accessingdatamongodb;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author gilbertwang
 */
@RestController
@Slf4j
public class AccessingDataMongodbController {

	@Autowired
	private EmployeeRepository repository;

	@GetMapping("/mongodb/first")
	public Employee findByFirstName(
			@RequestParam(value="name", required=false, defaultValue="Bob")String name) {
		initData();
		return repository.findByFirstName(name).get(0);
	}

	@GetMapping("/mongodb/last")
	public Employee findByLastName(
			@RequestParam(value="name", required=false, defaultValue="Smith")String name) {
		initData();
		return repository.findByLastName(name).get(0);
	}

	public void initData(){
		if(repository.count() > 0)
		{
			return;
		}
		// save a couple of customers
		repository.save(new Employee("Alice", "Smith"));
		repository.save(new Employee("Bob", "Smith"));

		// fetch all customers
		for (Employee employee : repository.findAll()) {
			log.debug(employee.toString());
		}
	}
}
