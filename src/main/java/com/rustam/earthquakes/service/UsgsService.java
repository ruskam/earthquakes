package com.rustam.earthquakes.service;

import com.rustam.earthquakes.model.*;
import com.rustam.earthquakes.repository.IUsgsRepository;
import com.rustam.earthquakes.util.IDistance;
import com.rustam.earthquakes.util.IPrinterToConsole;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class UsgsService implements IUsgsService{

    private static final int NUMBER_CLOSEST_EARTHQUAKE_SITES = 10;

    private final IUsgsRepository repository;
    private final IDistance distance;
    private final IPrinterToConsole printer;

    public UsgsService(IUsgsRepository repository, IDistance distance, IPrinterToConsole printer) {
        this.repository = repository;
        this.distance = distance;
        this.printer = printer;
    }

    @Override
    public EarthquakeWrapper getEarthquake(double lat, double lon) {

        IUsgsResponse response = repository.getUsgsResponse(lat, lon);
        List<Earthquake> earthquakes = sortFilter(populateEarthquakes(response, lat, lon));
        EarthquakeWrapper earthquakeWrapper = new EarthquakeWrapper();
        earthquakeWrapper.setEarthquakes(earthquakes);

        printer.print(earthquakes);

        return earthquakeWrapper;
    }

    private List<Earthquake> populateEarthquakes(IUsgsResponse response, double lat, double lon) {
        List<Earthquake> earthquakes = new ArrayList<>(10);

        Set<GeoLocation> coords = new HashSet<>();

        Earthquake earthquake;
        for (int i = 0; i < response.getFeatures().size(); i++) {
            int distanceCalculated = distance.calculate(lat, lon,
                    response.getFeatures().get(i).getGeometry().getLat(),
                    response.getFeatures().get(i).getGeometry().getLon());

            GeoLocation geoLocation = new GeoLocation(response.getFeatures().get(i).getGeometry().getLat(), response.getFeatures().get(i).getGeometry().getLon());
            if (!coords.contains(geoLocation)) {
                earthquake = new EarthquakeBuilder()
                        .setId(response.getFeatures().get(i).getId())
                        .setMagnitude(response.getFeatures().get(i).getProperties().getMag())
                        .setTitle(response.getFeatures().get(i).getProperties().getTitle())
                        .setDistance(distanceCalculated)
                        .setCoordinates(geoLocation)
                        .buildEarthquake();
                earthquakes.add(earthquake);
                coords.add(geoLocation);
            }
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
