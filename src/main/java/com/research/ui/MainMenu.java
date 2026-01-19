package com.research.ui;

import com.research.service.*;

import static com.research.util.InputUtil.readInt;

public class MainMenu {

    private final ResidentMenu residentMenu;
    private final VehicleMenu vehicleMenu;
    private final GateLaneMenu gateLaneMenu;
    private final TrafficMenu trafficMenu;
    private final VisitorMenu visitorMenu;

    public MainMenu(ResidentService residentService,
                    VehicleService vehicleService,
                    GateLaneService gateLaneService,
                    GatePassService gatePassService,
                    VisitReservationService visitReservationService) {

        this.residentMenu = new ResidentMenu(residentService);
        this.vehicleMenu = new VehicleMenu(vehicleService);
        this.gateLaneMenu = new GateLaneMenu(gateLaneService);
        this.trafficMenu = new TrafficMenu(gatePassService);
        this.visitorMenu = new VisitorMenu(visitReservationService);
    }

    public void start() {
        while (true) {
            System.out.println("\n===== COMPOUND GATE MANAGEMENT SYSTEM =====");
            System.out.println("1. Resident Management");
            System.out.println("2. Vehicle Management");
            System.out.println("3. Gate Lane Management");
            System.out.println("4. Traffic Management");
            System.out.println("5. Visitor Management");
            System.out.println("0. Exit");

            int choice = readInt("Choose option: ");

            switch (choice) {
                case 1 -> residentMenu.show();
                case 2 -> vehicleMenu.show();
                case 3 -> gateLaneMenu.show();
                case 4 -> trafficMenu.show();
                case 5 -> visitorMenu.show();
                case 0 -> {
                    System.out.println("👋 Exiting system. Goodbye!");
                    return;
                }
                default -> System.out.println("❌ Invalid option.");
            }
        }
    }
}
