package com.rustam.earthquakes.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
public class UsgsProperty {

    private Double mag;
    private String title;

    public UsgsProperty(Double mag, String title) {
        this.mag = mag;
        this.title = title;
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
