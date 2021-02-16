package com.rustam.earthquakes.repository;

import com.rustam.earthquakes.model.UsgsResponse;
import reactor.core.publisher.Mono;

public interface IUsgsRepository {
    UsgsResponse getUsgsResponse(double lat, double lon);
    Mono<UsgsResponse> getUsgsResponseAsync(double lat, double lon);
}
