package com.rustam.earthquakes.service;

import com.rustam.earthquakes.model.Earthquake;
import com.rustam.earthquakes.model.EarthquakeBuilder;
import com.rustam.earthquakes.model.GeoLocation;
import org.hamcrest.collection.IsEmptyCollection;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.MatcherAssert.assertThat;

@SpringBootTest
public class ServiceTest {

    @Autowired
    private IUsgsService service;

    @Test
    public void testJsonObjectList(){
        List<Earthquake> earthquakes = service.getEarthquake(40.71427, -74.00597);
        Assertions.assertNotNull(earthquakes);
        Assertions.assertEquals(10, earthquakes.size());
    }

    @Test
    public void testListContainsObject(){
        Earthquake earthquake = new EarthquakeBuilder()
                .setId("us7000cmj2")
                .setMagnitude(2.1)
                .setTitle("M 2.1 - 0 km ENE of Milford, New Jersey")
                .setDistance(92)
                .setCoordinates(new GeoLocation(40.5721, -75.0839))
                .buildEarthquake();

        List<Earthquake> earthquakes = service.getEarthquake(40.71427, -74.00597);
        assertThat(earthquakes.size(), is(10));
        assertThat(earthquakes, not(IsEmptyCollection.empty()));
        Assertions.assertEquals(earthquake, earthquakes.get(0));

    }
}
