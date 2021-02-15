package com.rustam.earthquakes.model;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class UsgsResponse implements IUsgsResponse{

    private List<UsgsObservation> features;

    public UsgsResponse() {
        this.features = new ArrayList<>();
    }
}
