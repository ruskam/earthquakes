package com.rustam.earthquakes.model;

public class UsgsGeometry {

    private Double[] coordinates;

    public UsgsGeometry() {
        coordinates = new Double[2];
    }

    public Double[] getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(Double[] coordinates) {
        this.coordinates = coordinates;
    }

    public double getLat() {
        return this.coordinates[1];
    }

    public double getLon() {
        return this.coordinates[0];
    }

}
