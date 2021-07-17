package com.gilbert.test.consumingrestweb;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author gilbertwang
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Greeting {

	private long id;
	private String content;
}
