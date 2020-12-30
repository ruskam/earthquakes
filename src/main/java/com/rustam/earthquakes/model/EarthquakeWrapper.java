package com.rustam.earthquakes.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class EarthquakeWrapper {

    private List<Earthquake> earthquakes;

}
