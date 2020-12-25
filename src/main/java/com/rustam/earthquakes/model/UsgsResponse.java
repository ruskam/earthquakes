package com.rustam.earthquakes.model;

import java.util.ArrayList;
import java.util.List;

public class UsgsResponse implements IUsgsResponse{

    private final List<UsgsObservation> features;

    public UsgsResponse() {
        this.features = new ArrayList<>();
    }

    @Override
    public List<UsgsObservation> getFeatures() {
        return features;
    }
}
