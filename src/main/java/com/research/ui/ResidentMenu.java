package com.research.ui;

import com.research.model.Resident;
import com.research.service.ResidentService;

import static com.research.util.InputUtil.readInt;
import static com.research.util.InputUtil.readString;

public class ResidentMenu {

    private final ResidentService residentService;

    public ResidentMenu(ResidentService residentService) {
        this.residentService = residentService;
    }

    public void show() {
        while (true) {
            System.out.println("\n--- Resident Management ---");
            System.out.println("1. Add Resident");
            System.out.println("2. View All Residents");
            System.out.println("3. Delete Resident");
            System.out.println("0. Back");

            int choice = readInt("Choose: ");

            try {
                switch (choice) {
                    case 1 -> addResident();
                    case 2 -> residentService.getAllResidents()
                            .forEach(r -> System.out.println(
                                    r.getId() + " | " + r.getFullName() + " | " + r.getUnitNumber()));
                    case 3 -> residentService.deleteResident(readInt("Resident ID: "));
                    case 0 -> { return; }
                    default -> System.out.println("Invalid option.");
                }
            } catch (Exception e) {
                System.out.println("❌ " + e.getMessage());
            }
        }
    }

    private void addResident() {
        Resident resident = new Resident(
                readInt("ID: "),
                readString("Full Name: "),
                readString("Email: "),
                readString("Phone: "),
                readString("Unit Number: ")
        );
        residentService.addResident(resident);
        System.out.println("✅ Resident added successfully.");
    }
}
