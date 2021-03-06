package com.rustam.earthquakes.model;

import java.time.LocalDate;

public class EarthquakeBuilder {
    private String id;
    private Double magnitude;
    private String title;
    private LocalDate date;
    private Integer distance;
    private GeoLocation coordinates;

    public EarthquakeBuilder setId(String id) {
        this.id = id;
        return this;
    }

    public EarthquakeBuilder setMagnitude(Double magnitude) {
        this.magnitude = magnitude;
        return this;
    }

    public EarthquakeBuilder setTitle(String title) {
        this.title = title;
        return this;
    }

    public EarthquakeBuilder setDate(LocalDate date) {
        this.date = date;
        return this;
    }

    public EarthquakeBuilder setDistance(Integer distance) {
        this.distance = distance;
        return this;
    }

    public EarthquakeBuilder setCoordinates(GeoLocation coordinates) {
        this.coordinates = coordinates;
        return this;
    }

    public Earthquake buildEarthquake(){
        return new Earthquake(id, magnitude, title, date, distance, coordinates);
    }
}
