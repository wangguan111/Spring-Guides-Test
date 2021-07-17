package com.gilbert.test.caching;

/**
 * @author gilbertwang
 */
public interface BookRepository {

	Book getByIsbn(String isbn);
}
