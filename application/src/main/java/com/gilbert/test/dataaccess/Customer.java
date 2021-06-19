package com.gilbert.test.dataaccess;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author gilbertwang
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Customer {

	private long id;
	private String firstName, lastName;
}
