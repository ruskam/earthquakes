package com.rustam.earthquakes.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
public class UsgsObservation {

    private UsgsProperty properties;
    private UsgsGeometry geometry;
    private String id;

    public UsgsObservation(UsgsProperty properties, UsgsGeometry geometry, String id) {
        this.properties = properties;
        this.geometry = geometry;
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
