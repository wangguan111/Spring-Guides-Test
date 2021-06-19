package com.gilbert.test.service;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author gilbertwang
 */
@ConfigurationProperties("service")
@Data
public class ServiceProperties {

	private String message;
}
