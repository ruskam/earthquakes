package com.rustam.earthquakes.model;

import java.util.List;

public interface IUsgsResponse {
    List<UsgsObservation> getFeatures();
    void setFeatures(List<UsgsObservation> features);
}
