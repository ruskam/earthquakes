package com.rustam.earthquakes;

import com.rustam.earthquakes.controller.EarthQuakeController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
class EarthquakesApplicationTests {

	@Autowired
	private EarthQuakeController controller;

	@Test
	void contextLoads() {
		assertThat(controller).isNotNull();
	}

}
