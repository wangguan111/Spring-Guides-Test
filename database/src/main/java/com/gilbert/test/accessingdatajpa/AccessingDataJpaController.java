package com.gilbert.test.accessingdatajpa;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author gilbertwang
 */
@RestController
@RequestMapping(path="/jpa")
@Slf4j
public class AccessingDataJpaController {

	@Autowired
	private CustomerRepository repository;

	private void initData()
	{
		if(repository.count() > 0)
		{
			return;
		}

		// save a few customers
		repository.save(new Customer("Jack", "Bauer"));
		repository.save(new Customer("Chloe", "O'Brian"));
		repository.save(new Customer("Kim", "Bauer"));
		repository.save(new Customer("David", "Palmer"));
		repository.save(new Customer("Michelle", "Dessler"));

		// fetch all customers
		log.debug("Customers found with findAll():");
		for (Customer customer: repository.findAll()) {
			log.debug(customer.toString());
		}
	}

	@GetMapping("/id")
	public Customer findById(
			@RequestParam(value="id", required=false, defaultValue="1")Long id) {
		initData();
		return repository.findById(id).get();
	}

	@GetMapping("/name")
	public Customer findByLastName(
			@RequestParam(value="name", required=false, defaultValue="Bauer")String name) {
		initData();
		return repository.findByLastName(name).get(0);
	}
}
