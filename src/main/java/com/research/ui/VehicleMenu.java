package com.research.ui;

import com.research.service.VehicleService;

import static com.research.util.InputUtil.readInt;

public class VehicleMenu {

    private final VehicleService vehicleService;

    public VehicleMenu(VehicleService vehicleService) {
        this.vehicleService = vehicleService;
    }

    public void show() {
        while (true) {
            System.out.println("\n--- Vehicle Management ---");
            System.out.println("1. View Vehicles");
            System.out.println("2. Delete Vehicle");
            System.out.println("0. Back");

            int choice = readInt("Choose: ");

            try {
                switch (choice) {
                    case 1 -> vehicleService.getAllVehicles()
                            .forEach(v -> System.out.println(
                                    v.getId() + " | " + v.getPlateNumber()));
                    case 2 -> vehicleService.deleteVehicle(readInt("Vehicle ID: "));
                    case 0 -> { return; }
                    default -> System.out.println("Invalid option.");
                }
            } catch (Exception e) {
                System.out.println("❌ " + e.getMessage());
            }
        }
    }
}
