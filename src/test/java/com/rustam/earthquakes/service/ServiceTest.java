package com.rustam.earthquakes.service;

import com.rustam.earthquakes.model.EarthquakeWrapper;
import org.hamcrest.collection.IsEmptyCollection;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.MatcherAssert.assertThat;

@SpringBootTest
class ServiceTest {

    @Autowired
    private IUsgsService service;

    @Test
    void testJsonObjectList(){
        EarthquakeWrapper earthquakeWrapper = service.getEarthquake(40.71427, -74.00597);
        Assertions.assertNotNull(earthquakeWrapper.getEarthquakes());
        Assertions.assertEquals(10, earthquakeWrapper.getEarthquakes().size());
    }

    @Test
    void testListContainsObject(){
        EarthquakeWrapper earthquakeWrapper = service.getEarthquake(40.71427, -74.00597);
        assertThat(earthquakeWrapper.getEarthquakes().size(), is(10));
        assertThat(earthquakeWrapper.getEarthquakes(), not(IsEmptyCollection.empty()));

    }
}
