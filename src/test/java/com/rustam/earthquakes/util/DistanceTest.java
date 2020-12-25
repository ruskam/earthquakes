package com.rustam.earthquakes.util;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class DistanceTest {

    @Autowired
    IDistance distance;

    @Test
    void testCalculate(){
        double lat1 = 40.730610;
        double lon1 = -73.935242;

        double lat2 = 44.8009;
        double lon2 = -74.9910;

        int actual = distance.calculate(lat1, lon1, lat2, lon2);
        Assertions.assertEquals(461, actual);

    }
}
