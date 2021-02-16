package com.rustam.earthquakes.service;

import com.rustam.earthquakes.model.*;
import com.rustam.earthquakes.repository.IUsgsRepository;
import com.rustam.earthquakes.util.Distance;
import com.rustam.earthquakes.util.IDistance;
import com.rustam.earthquakes.util.IPrinterToConsole;
import com.rustam.earthquakes.util.PrinterToConsole;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyDouble;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UsgsServiceUnitTest {
    private IDistance distance;
    private IPrinterToConsole printer;
    UsgsResponse response;

    @Mock
    private IUsgsRepository mockedRepository;

    @BeforeEach
    void beforeEach() {
        distance = new Distance();
        printer = new PrinterToConsole();
        response = new UsgsResponse();
        response.setFeatures(getFeatures());
    }

    @Test
    void testGetEarthquakeException(){
        final UsgsService service = mock(UsgsService.class);

        when(service.getEarthquake(anyDouble(), anyDouble())).thenThrow(IllegalArgumentException.class);

        final IllegalArgumentException exception = Assertions.assertThrows(
                IllegalArgumentException.class, () -> service.getEarthquake(12.12,15.15));

        assertThat(exception.getClass(), is(equalTo(IllegalArgumentException.class)));
    }

    @Test
    void getEarthquake() {
        assertNotNull(mockedRepository);
        when(mockedRepository.getUsgsResponse(anyDouble(), anyDouble())).thenReturn(response);
        IUsgsService service = new UsgsService(mockedRepository, distance, printer);

        EarthquakeWrapper earthquakeWrapper = service.getEarthquake(12.12, 15.15);
        assertNotNull(earthquakeWrapper);
        assertEquals(4, earthquakeWrapper.getEarthquakes().size());
    }

    @Test
    void populateEarthquakes() {
        assertNotNull(mockedRepository);
        lenient().when(mockedRepository.getUsgsResponse(anyDouble(), anyDouble())).thenReturn(response);
        UsgsService service = new UsgsService(mockedRepository, distance, printer);
        List<Earthquake> earthquakes = service.populateEarthquakes(response, 12.34, 56.78);

        assertEquals(4, earthquakes.size());
        assertEquals(new HashSet<>(earthquakes).size(), earthquakes.size());
    }

    @Test
    void getEarthquakeAsync() {
        UsgsService service = new UsgsService(mockedRepository, distance, printer);
        assertNotNull(mockedRepository);
//        Mono<UsgsResponse> mono = Mono.just(response);
//        when(mockedRepository.getUsgsResponseAsync(anyDouble(), anyDouble())).thenReturn(mono);

    }

    List<UsgsObservation> getFeatures(){
        UsgsObservation feature1 = new UsgsObservation(new UsgsProperty(3.01, "Place1", 1613213315880L), new UsgsGeometry(new Double[]{39.2999, 45.23984}), "1");
        UsgsObservation feature2 = new UsgsObservation(new UsgsProperty(1.45, "Place2", 1613210249310L), new UsgsGeometry(new Double[]{34.2939, 48.23533}), "2");
        UsgsObservation feature3 = new UsgsObservation(new UsgsProperty(3.78, "Place3", 1613213115880L), new UsgsGeometry(new Double[]{9.291939, 25.2534}), "3");
        UsgsObservation feature4 = new UsgsObservation(new UsgsProperty(5.55, "Place4", 1613213015880L), new UsgsGeometry(new Double[]{23.456, 68.9863}), "4");
        UsgsObservation feature5 = new UsgsObservation(new UsgsProperty(3.02, "Place1", 1613213315880L), new UsgsGeometry(new Double[]{39.2999, 45.23984}), "5");
        List<UsgsObservation> features = new ArrayList<>();
        features.add(feature1);
        features.add(feature2);
        features.add(feature3);
        features.add(feature4);
        features.add(feature5);
        return features;
    }

    @Test
    void distinctByKey() {
        UsgsService service = new UsgsService(mockedRepository, distance, printer);
        List<Earthquake> earthquakes = new ArrayList<>();
        Earthquake e1 = new EarthquakeBuilder().setId("1")
                .setMagnitude(1.3)
                .setDate(LocalDate.of(2021, 1, 12))
                .setCoordinates(new GeoLocation(44.4806, 80.1145))
                .buildEarthquake();
        Earthquake e2 = new EarthquakeBuilder().setId("2")
                .setMagnitude(4.1)
                .setDate(LocalDate.of(2001, 1, 12))
                .setCoordinates(new GeoLocation(44.4806, 80.1145))
                .buildEarthquake();
        Earthquake e3 = new EarthquakeBuilder().setId("3")
                .setMagnitude(2.6)
                .setDate(LocalDate.of(2020, 11, 2))
                .setCoordinates(new GeoLocation(48.34806, 56.76543))
                .buildEarthquake();
        earthquakes.add(e1);
        earthquakes.add(e2);
        earthquakes.add(e3);

        List<Earthquake> actual = earthquakes.stream()
                .filter(service.distinctByKey(Earthquake::getCoordinates))
                .collect(Collectors.toList());

        assertTrue(actual.contains(e1));
        assertFalse(actual.contains(e2));
        assertTrue(actual.contains(e3));
    }
}