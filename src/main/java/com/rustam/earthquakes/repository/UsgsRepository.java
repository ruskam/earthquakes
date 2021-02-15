package com.rustam.earthquakes.repository;

import com.rustam.earthquakes.model.UsgsResponse;
import org.springframework.stereotype.Repository;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Repository
public class UsgsRepository implements IUsgsRepository {

    private static final String USGS_DATA_URL = "https://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_month.geojson";

    private final WebClient webClient;

    public UsgsRepository(WebClient webClient) {
        this.webClient = webClient;
    }

    /**
     * This is NOT reactive way. WebClient is used because it fetches the data faster than RestTemplate
     * @param lat
     * @param lon
     * @return
     */
    @Override
    public UsgsResponse getUsgsResponse(double lat, double lon) {
        UsgsResponse response = this.webClient
                .get()
                .uri(USGS_DATA_URL)
                .retrieve()
                .bodyToMono(UsgsResponse.class)
                .block();

        return this.webClient
                .get()
                .uri(USGS_DATA_URL)
                .retrieve()
                .bodyToMono(UsgsResponse.class)
                .block();
    }

    /**
     * This is sort of reactive based on what I have learnt so far
     * @param lat
     * @param lon
     * @return
     */
    @Override
    public Mono<UsgsResponse> getUsgsResponseAsync(double lat, double lon) {
        return this.webClient
                .get()
                .uri(USGS_DATA_URL)
                .retrieve()
                .bodyToMono(UsgsResponse.class);
    }
}

