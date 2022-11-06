package com.aston.colomb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication
public class ColombApplication {

	public static void main(String[] args) {
		SpringApplication.run(ColombApplication.class, args);
	}

}
