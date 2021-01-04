package com.rustam.earthquakes.service;

import com.rustam.earthquakes.model.Earthquake;
import com.rustam.earthquakes.model.EarthquakeWrapper;
import reactor.core.publisher.Flux;

public interface IUsgsService {

    EarthquakeWrapper getEarthquake(double lat, double lon);
    Flux<Earthquake> getEarthquakeAsync(double lat, double lon);
}
