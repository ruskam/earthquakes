package com.rustam.earthquakes.service;

import com.rustam.earthquakes.model.Earthquake;

import java.util.List;

public interface IUsgsService {

    List<Earthquake> getEarthquake(double lat, double lon);
}
