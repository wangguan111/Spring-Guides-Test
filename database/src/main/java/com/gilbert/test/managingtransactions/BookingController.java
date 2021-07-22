package com.gilbert.test.managingtransactions;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

/**
 * @author gilbertwang
 */
@RestController
@RequestMapping(path="/book")
@Slf4j
public class BookingController {

    @Autowired
    private BookingService bookingService;

    @GetMapping("/all")
    public String bookAll()
    {
        bookingService.deleteAll();
        bookingService.book("Alice", "Bob", "Carol");
        log.debug("Alice, Bob and Carol have been booked");
        return bookingService.findAllBookings().get(2);
    }

    @GetMapping("/big")
    public List<String> bookBig()
    {
        try {
            bookingService.deleteAll();
            bookingService.book("Chris", "Samuel");
        } catch (RuntimeException e) {
            log.debug("The following exception is expect because 'Samuel' is too big for the DB");
        }
        return bookingService.findAllBookings();
    }

    @GetMapping("/null")
    public List<String> bookNull()
    {
        try {
            bookingService.deleteAll();
            bookingService.book("Buddy", null);
        } catch (RuntimeException e) {
            log.debug("The following exception is expect because null is not valid for the DB");
        }
        return bookingService.findAllBookings();
    }
}
