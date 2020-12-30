package com.rustam.earthquakes.controller;

import com.rustam.earthquakes.exception.UsgsResourceNotFoundException;
import com.rustam.earthquakes.model.Earthquake;
import com.rustam.earthquakes.model.EarthquakeWrapper;
import com.rustam.earthquakes.model.UsgsResponse;
import com.rustam.earthquakes.service.IUsgsService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import reactor.core.publisher.Mono;

import java.util.List;

@RestController
@RequestMapping(value = ControllerConstants.EARTH_QUAKE_ENDPOINT)
public class EarthQuakeController {

    private final IUsgsService service;

    public EarthQuakeController(IUsgsService service) {
        this.service = service;
    }

    @GetMapping(value = "/lat/{lat}/lon/{lon}")
    public ResponseEntity<EarthquakeWrapper> getEQ(@PathVariable("lat") double lat, @PathVariable("lon") double lon) {
        try {
            List<Earthquake> earthquakes = service.getEarthquake(lat, lon);

            EarthquakeWrapper earthquakeWrapper = new EarthquakeWrapper();
            earthquakeWrapper.setEarthquakes(earthquakes);
//            return ResponseEntity.ok().body(earthquakes);
            return ResponseEntity.ok().body(earthquakeWrapper);
        } catch (UsgsResourceNotFoundException e) {
            throw new ResponseStatusException(
                HttpStatus.NOT_FOUND, "something went wrong for location" +
                lat + ", " + lon, e);
        }
    }

    @GetMapping(value = "/async/lat/{lat}/lon/{lon}")
    public Mono<UsgsResponse> getEQAsync(@PathVariable("lat") double lat, @PathVariable("lon") double lon) {
        try {
            Mono<UsgsResponse> earthquakeAsync = service.getEarthquakeAsync(lat, lon);
//            return ResponseEntity.ok().body(earthquakeAsync);
                        return earthquakeAsync;

        } catch (UsgsResourceNotFoundException e) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "something went wrong for location" +
                    lat + ", " + lon, e);
        }
    }

}
