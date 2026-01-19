package com.research.service;

import com.research.exception.BusinessRuleViolationException;
import com.research.model.Vehicle;
import com.research.repository.VehicleRepository;

import java.util.List;

public class VehicleService {

    private final VehicleRepository vehicleRepository;
    private final ValidationService validationService;

    public VehicleService(VehicleRepository vehicleRepository,
                          ValidationService validationService) {
        this.vehicleRepository = vehicleRepository;
        this.validationService = validationService;
    }

    public void registerVehicle(Vehicle vehicle) {
        validationService.validateId(vehicle.getId());
        validationService.validateRequiredString(vehicle.getPlateNumber(), "Plate Number");
        validationService.validateNotNull(vehicle.getOwner(), "Vehicle Owner");
        validationService.validateNotNull(vehicle.getVehicleType(), "Vehicle Type");

        if (vehicleRepository.existsByPlateNumber(vehicle.getPlateNumber())) {
            throw new BusinessRuleViolationException("Duplicate plate number not allowed.");
        }

        vehicleRepository.add(vehicle);
    }

    public Vehicle getVehicleById(int id) {
        return vehicleRepository.getById(id);
    }

    public List<Vehicle> getAllVehicles() {
        return List.copyOf(vehicleRepository.getAll());
    }

    public void deleteVehicle(int id) {
        vehicleRepository.delete(id);
    }
}
