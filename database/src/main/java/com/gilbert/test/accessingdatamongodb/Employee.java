package com.gilbert.test.accessingdatamongodb;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

/**
 * @author gilbertwang
 */
@Data
@NoArgsConstructor
public class Employee {

	@Id
	public String id;

	public String firstName;
	public String lastName;

	public Employee(String firstName, String lastName) {
		this.firstName = firstName;
		this.lastName = lastName;
	}
}

