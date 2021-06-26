package com.gilbert.test.caching;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.io.Serializable;

/**
 * @author gilbertwang
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Book implements Serializable {

	private static final long serialVersionUID = 5623258412069063747L;
	private String isbn;
	private String title;
}
