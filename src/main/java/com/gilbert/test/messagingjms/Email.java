package com.gilbert.test.messagingjms;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author gilbertwang
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Email {
    private String to;
    private String body;
}
