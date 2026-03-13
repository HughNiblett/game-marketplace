package com.github.hughniblett.marketplace.state;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class StateApplication {

	private static final Logger logger = LoggerFactory.getLogger(StateApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(StateApplication.class, args);
	}
}
