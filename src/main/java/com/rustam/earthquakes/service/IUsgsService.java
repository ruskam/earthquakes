package com.rustam.earthquakes.service;

import com.rustam.earthquakes.model.EarthquakeWrapper;

public interface IUsgsService {

    EarthquakeWrapper getEarthquake(double lat, double lon);
}
