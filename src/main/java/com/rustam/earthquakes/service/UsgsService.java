package com.rustam.earthquakes.service;

import com.rustam.earthquakes.model.*;
import com.rustam.earthquakes.repository.IUsgsRepository;
import com.rustam.earthquakes.util.IDistance;
import com.rustam.earthquakes.util.IPrinterToConsole;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@Service
public class UsgsService implements IUsgsService {

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
        if (isLocationValid(lat, lon)) {
            IUsgsResponse response = repository.getUsgsResponse(lat, lon);
            List<Earthquake> earthquakes = populateEarthquakes(response, lat, lon);
            EarthquakeWrapper earthquakeWrapper = new EarthquakeWrapper();
            earthquakeWrapper.setEarthquakes(earthquakes);

            printer.print(earthquakes);

            return earthquakeWrapper;
        } else {
            throw new IllegalArgumentException("Latitude should be between -90 and 90 degrees");
        }
    }

    private boolean isLocationValid(double lat, double lon) {
        if ((lat >= -90 && lat <= 90) && (lon >= -180 && lon <= 180)) {
            return true;
        }
        return false;
    }

    public List<Earthquake> populateEarthquakes(IUsgsResponse response, double lat, double lon) {

        return response.getFeatures().stream()
                .map(feature -> {
                    double lati = feature.getGeometry().getLat();
                    double longi = feature.getGeometry().getLon();
                    return new EarthquakeBuilder()
                            .setId(feature.getId())
                            .setMagnitude(feature.getProperties().getMag())
                            .setTitle(feature.getProperties().getTitle())
                            .setDistance(distance.calculate(lat, lon, lati, longi))
                            .setCoordinates(new GeoLocation(lati, longi))
                            .buildEarthquake();
                })
                .filter(distinctByKey(Earthquake::getCoordinates))
                .sorted(Comparator.comparingInt(Earthquake::getDistance))
                .limit(NUMBER_CLOSEST_EARTHQUAKE_SITES)
                .collect(Collectors.toList());
    }

    @Override
    public Flux<Earthquake> getEarthquakeAsync(double lat, double lon) {

        return repository.getUsgsResponseAsync(lat, lon)
                .map(response -> response.getFeatures()
                        .stream()
                    .map(feat -> {
                        double lati = feat.getGeometry().getLat();
                        double longi = feat.getGeometry().getLon();
                        return new EarthquakeBuilder()
                                .setId(feat.getId())
                                .setMagnitude(feat.getProperties().getMag())
                                .setTitle(feat.getProperties().getTitle())
                                .setDistance(distance.calculate(lat, lon, lati, longi))
                                .setCoordinates(new GeoLocation(lati, longi))
                                .buildEarthquake();
                    })
                    .sorted(Comparator.comparingInt(Earthquake::getDistance))
                    .limit(NUMBER_CLOSEST_EARTHQUAKE_SITES)
                    .collect(Collectors.toList()))
                .flatMapMany(Flux::fromIterable);

    }

    private <T> Predicate<T> distinctByKey(Function<? super T, GeoLocation> keyExtractor)
    {
        Map<GeoLocation, Boolean> map = new ConcurrentHashMap<>();
        return t -> map.putIfAbsent(keyExtractor.apply(t), Boolean.TRUE) == null;
    }
}
