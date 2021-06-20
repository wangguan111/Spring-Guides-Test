package com.gilbert.test.managingtransactions;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

/**
 * @author gilbertwang
 */
@RestController
@Slf4j
public class BookingController {

    @Autowired
    private BookingService bookingService;

    @GetMapping("/book/all")
    public String bookAll()
    {
        bookingService.deleteAll();
        bookingService.book("Alice", "Bob", "Carol");
        log.info("Alice, Bob and Carol have been booked");
        return bookingService.findAllBookings().get(2);
    }

    @GetMapping("/book/big")
    public List<String> bookBig()
    {
        try {
            bookingService.deleteAll();
            bookingService.book("Chris", "Samuel");
        } catch (RuntimeException e) {
            log.info("The following exception is expect because 'Samuel' is too big for the DB");
        }
        return bookingService.findAllBookings();
    }

    @GetMapping("/book/null")
    public List<String> bookNull()
    {
        try {
            bookingService.deleteAll();
            bookingService.book("Buddy", null);
        } catch (RuntimeException e) {
            log.info("The following exception is expect because null is not valid for the DB");
        }
        return bookingService.findAllBookings();
    }
}
