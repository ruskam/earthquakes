package com.rustam.earthquakes.service;

import com.rustam.earthquakes.model.Earthquake;
import com.rustam.earthquakes.model.EarthquakeBuilder;
import com.rustam.earthquakes.model.IUsgsResponse;
import com.rustam.earthquakes.repository.IUsgsRepository;
import com.rustam.earthquakes.util.IDistance;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UsgsService implements IUsgsService{

    private static final int NUMBER_CLOSEST_EARTHQUAKE_SITES = 10;

    private final IUsgsRepository repository;
    private final IDistance distance;

    public UsgsService(IUsgsRepository repository, IDistance distance) {
        this.repository = repository;
        this.distance = distance;
    }

    @Override
    public List<Earthquake> getEarthquake(double lat, double lon) {
        IUsgsResponse response = repository.getUsgsResponse(lat, lon);

        return sortFilter(populateEarthquakes(response, lat, lon));
    }

    private List<Earthquake> populateEarthquakes(IUsgsResponse response, double lat, double lon) {
        List<Earthquake> earthquakes = new ArrayList<>(10);

        Earthquake earthquake;
        for (int i = 0; i < response.getFeatures().size(); i++) {
            int distanceCalculated = distance.calculate(lat, lon,
                    response.getFeatures().get(i).getGeometry().getLat(),
                    response.getFeatures().get(i).getGeometry().getLon());

            earthquake = new EarthquakeBuilder()
                    .setId(response.getFeatures().get(i).getId())
                    .setMagnitude(response.getFeatures().get(i).getProperties().getMag())
                    .setTitle(response.getFeatures().get(i).getProperties().getTitle())
                    .setDistance(distanceCalculated)
                    .buildEarthquake();

            earthquakes.add(earthquake);
        }

        return earthquakes;
    }

    private List<Earthquake> sortFilter(List<Earthquake> earthquakes) {
        return earthquakes.stream()
                .sorted(Comparator.comparingInt(Earthquake::getDistance))
                .limit(NUMBER_CLOSEST_EARTHQUAKE_SITES)
                .collect(Collectors.toList());
    }


}
