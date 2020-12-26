package com.rustam.earthquakes.repository;

import com.rustam.earthquakes.model.UsgsResponse;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;

@Repository
public class UsgsRepository implements IUsgsRepository {

    private static final String USGS_DATA_URL = "https://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_month.geojson";

    /** I am aware of the WebClient but the present solution features RestTemplate*/
    private final RestTemplate restTemplate;

    public UsgsRepository(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public UsgsResponse getUsgsResponse(double lat, double lon) {

        return restTemplate.getForEntity(
                USGS_DATA_URL,
                UsgsResponse.class)
                .getBody();
    }
}
