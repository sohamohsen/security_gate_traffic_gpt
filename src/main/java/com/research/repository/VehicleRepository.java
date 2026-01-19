package com.research.repository;

import com.research.model.Vehicle;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class VehicleRepository extends BaseRepository<Vehicle> {

    // ---------------- FIND BY PLATE NUMBER ----------------
    public Optional<Vehicle> findByPlateNumber(String plateNumber) {
        return storage.values()
                .stream()
                .filter(vehicle ->
                        vehicle.getPlateNumber().equalsIgnoreCase(plateNumber))
                .findFirst();
    }

    // ---------------- CHECK PLATE EXISTS ----------------
    public boolean existsByPlateNumber(String plateNumber) {
        return storage.values()
                .stream()
                .anyMatch(vehicle ->
                        vehicle.getPlateNumber().equalsIgnoreCase(plateNumber));
    }

    // ---------------- SEARCH BY OWNER ID ----------------
    public List<Vehicle> findByOwnerId(int ownerId) {
        return storage.values()
                .stream()
                .filter(vehicle ->
                        vehicle.getOwner() != null &&
                                vehicle.getOwner().getId() == ownerId)
                .collect(Collectors.toList());
    }
}
