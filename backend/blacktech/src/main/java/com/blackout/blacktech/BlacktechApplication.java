package com.blackout.blacktech;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = SecurityAutoConfiguration.class)
public class BlacktechApplication {

	public static void main(String[] args) {
		SpringApplication.run(BlacktechApplication.class, args);
	}

}
