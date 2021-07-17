package com.gilbert.test.accessingdatajpa;

import java.util.List;
import org.springframework.data.repository.CrudRepository;

/**
 * @author gilbertwang
 */
public interface CustomerRepository extends CrudRepository<Customer, Long> {

	List<Customer> findByLastName(String lastName);
}
