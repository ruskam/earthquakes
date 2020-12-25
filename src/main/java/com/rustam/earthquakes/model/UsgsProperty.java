package com.rustam.earthquakes.model;

import java.util.Objects;

public class UsgsProperty {

    private Double mag;
    private String title;

    public UsgsProperty() {
    }

    public UsgsProperty(Double mag, String title) {
        this.mag = mag;
        this.title = title;
    }

    public Double getMag() {
        return mag;
    }

    public String getTitle() {
        return title;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UsgsProperty)) return false;
        UsgsProperty that = (UsgsProperty) o;
        return Objects.equals(getMag(), that.getMag()) && Objects.equals(getTitle(), that.getTitle());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getMag(), getTitle());
    }
}
