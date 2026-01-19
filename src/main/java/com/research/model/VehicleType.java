package com.research.model;

import com.research.repository.Identifiable;

public class VehicleType implements Identifiable {

    private int id;
    private String name;
    private String description;

    public VehicleType(int id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
    }

    @Override
    public int getId() {
        return id;
    }

    public String getName() { return name; }
    public String getDescription() { return description; }
}
