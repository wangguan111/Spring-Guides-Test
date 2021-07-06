package com.gilbert.test.caching;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author gilbertwang
 */
@RestController
@Slf4j
public class BookController {

	@Autowired
	private BookRepository bookRepository;

	@GetMapping("/caching")
	public Book book() throws Exception {
		log.debug(".... Fetching books");
		long start = System.currentTimeMillis();
		String[] arr = {"isbn-1234","isbn-4567","isbn-1234","isbn-4567"};
		for(String str: arr)
		{
			Book b = get(str);
		}
		log.debug("Elapsed time: " + (System.currentTimeMillis() - start));
		return get(arr[0]);
	}

	private Book get(String book) throws Exception{
		Book b = bookRepository.getByIsbn(book);
		log.debug(b.toString());
		return b;
	}
}
