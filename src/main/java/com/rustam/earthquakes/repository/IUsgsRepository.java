package com.rustam.earthquakes.repository;

import com.rustam.earthquakes.model.UsgsResponse;

public interface IUsgsRepository {
    UsgsResponse getUsgsResponse(double lat, double lon);
}
