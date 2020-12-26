package com.rustam.earthquakes.repository;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.client.RestTemplate;

@SpringBootTest
public class RepositoryTest {

    private static final String PORT = "8081";

    @Autowired
    private RestTemplate restTemplate;

    @Test
    public void testResposeNotNull(){
        String object = this.restTemplate.getForObject("http://localhost:"
                        + PORT
                        + "/api/earthquake/lat/40.71427/lon/-74.00597",
                String.class);
        Assertions.assertNotNull(object);

    }


}
