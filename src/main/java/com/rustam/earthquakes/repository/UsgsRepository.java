package com.rustam.earthquakes.repository;

import com.rustam.earthquakes.model.UsgsResponse;
import org.springframework.stereotype.Repository;
import org.springframework.web.reactive.function.client.WebClient;

@Repository
public class UsgsRepository implements IUsgsRepository {

    private static final String USGS_DATA_URL = "https://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_month.geojson";

    private final WebClient webClient;

    public UsgsRepository(WebClient webClient) {
        this.webClient = webClient;
    }

    @Override
    public UsgsResponse getUsgsResponse(double lat, double lon) {
    /* I am aware it is not a reactive solution since i block the response. I am only using WebClient in here */
        return this.webClient
                .get()
                .uri(USGS_DATA_URL)
                .retrieve()
                .bodyToMono(UsgsResponse.class)
                .block();
    }
}

