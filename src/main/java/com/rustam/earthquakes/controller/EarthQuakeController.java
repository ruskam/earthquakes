package com.rustam.earthquakes.controller;

import com.rustam.earthquakes.exception.UsgsResourceNotFoundException;
import com.rustam.earthquakes.model.Earthquake;
import com.rustam.earthquakes.model.EarthquakeWrapper;
import com.rustam.earthquakes.service.IUsgsService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import reactor.core.publisher.Flux;

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
            EarthquakeWrapper earthquakes = service.getEarthquake(lat, lon);
            return ResponseEntity.ok().body(earthquakes);
        } catch (IllegalArgumentException e) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_ACCEPTABLE, "Coordinates " +
                    lat + ", " + lon + " are out of bounds", e);
        }
        catch (UsgsResourceNotFoundException e) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "No URL requested" , e);
        }

    }

    @GetMapping(value = "/async/lat/{lat}/lon/{lon}")
    public ResponseEntity<Flux<Earthquake>> getEQAsync(@PathVariable("lat") double lat, @PathVariable("lon") double lon) {
        try {
            Flux<Earthquake> earthquakeAsync = service.getEarthquakeAsync(lat, lon);
            return ResponseEntity.ok().body(earthquakeAsync);
        } catch (IllegalArgumentException e) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_ACCEPTABLE, "Coordinates " +
                    lat + ", " + lon + " are out of bounds", e);
        }
        catch (UsgsResourceNotFoundException e) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "No URL requested" , e);
        }
    }

}
