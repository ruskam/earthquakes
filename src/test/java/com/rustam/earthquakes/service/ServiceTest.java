package com.rustam.earthquakes.service;

import com.rustam.earthquakes.model.Earthquake;
import com.rustam.earthquakes.model.EarthquakeBuilder;
import com.rustam.earthquakes.model.EarthquakeWrapper;
import com.rustam.earthquakes.model.GeoLocation;
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
        Earthquake earthquake = new EarthquakeBuilder()
                .setId("us7000cmj2")
                .setMagnitude(2.1)
                .setTitle("M 2.1 - 0 km ENE of Milford, New Jersey")
                .setDistance(92)
                .setCoordinates(new GeoLocation(40.5721, -75.0839))
                .buildEarthquake();

        EarthquakeWrapper earthquakeWrapper = service.getEarthquake(40.71427, -74.00597);
        assertThat(earthquakeWrapper.getEarthquakes().size(), is(10));
        assertThat(earthquakeWrapper.getEarthquakes(), not(IsEmptyCollection.empty()));
        Assertions.assertEquals(earthquake, earthquakeWrapper.getEarthquakes().get(0));

    }
}
