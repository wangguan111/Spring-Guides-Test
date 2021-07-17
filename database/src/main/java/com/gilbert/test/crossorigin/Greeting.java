package com.gilbert.test.crossorigin;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author gilbertwang
 */
@Data
@AllArgsConstructor
public class Greeting {

	private final long id;
	private final String content;

	public Greeting() {
		this.id = -1;
		this.content = "";
	}
}
