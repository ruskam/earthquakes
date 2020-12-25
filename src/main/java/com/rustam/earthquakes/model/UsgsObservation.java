package com.rustam.earthquakes.model;

import java.util.Objects;

public class UsgsObservation {

    private UsgsProperty properties;
    private UsgsGeometry geometry;
    private String id;

    public UsgsObservation() {
    }

    public UsgsObservation(UsgsProperty properties, UsgsGeometry geometry, String id) {
        this.properties = properties;
        this.geometry = geometry;
        this.id = id;
    }

    public UsgsProperty getProperties() {
        return properties;
    }

    public void setProperties(UsgsProperty properties) {
        this.properties = properties;
    }

    public UsgsGeometry getGeometry() {
        return geometry;
    }

    public void setGeometry(UsgsGeometry geometry) {
        this.geometry = geometry;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UsgsObservation)) return false;
        UsgsObservation that = (UsgsObservation) o;
        return Objects.equals(getProperties(), that.getProperties()) && Objects.equals(getGeometry(), that.getGeometry()) && Objects.equals(getId(), that.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getProperties(), getGeometry(), getId());
    }
}
