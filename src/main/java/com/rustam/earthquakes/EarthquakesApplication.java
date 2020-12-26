package com.rustam.earthquakes;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class EarthquakesApplication {

	public static void main(String[] args) {
		SpringApplication.run(EarthquakesApplication.class, args);
	}

}
