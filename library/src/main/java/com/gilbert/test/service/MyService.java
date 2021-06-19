package com.gilbert.test.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Service;

/**
 * @author gilbertwang
 */
@Service
@EnableConfigurationProperties(ServiceProperties.class)
public class MyService {

	@Autowired
	private ServiceProperties serviceProperties;

	public String message() {
		return this.serviceProperties.getMessage();
	}
}
