package com.research.ui;

import com.research.model.GateLane;
import com.research.model.LaneStatus;
import com.research.service.GateLaneService;

import static com.research.util.InputUtil.readInt;

public class GateLaneMenu {

    private final GateLaneService gateLaneService;

    public GateLaneMenu(GateLaneService gateLaneService) {
        this.gateLaneService = gateLaneService;
    }

    public void show() {
        while (true) {
            System.out.println("\n--- Gate Lane Management ---");
            System.out.println("1. Add Lane");
            System.out.println("2. Open Lane");
            System.out.println("3. Close Lane");
            System.out.println("0. Back");

            int choice = readInt("Choose: ");

            try {
                switch (choice) {
                    case 1 -> gateLaneService.addLane(new GateLane(
                            readInt("ID: "),
                            readInt("Lane Number: "),
                            readInt("Capacity/Minute: "),
                            LaneStatus.CLOSED));
                    case 2 -> gateLaneService.openLane(readInt("Lane ID: "));
                    case 3 -> gateLaneService.closeLane(readInt("Lane ID: "));
                    case 0 -> { return; }
                    default -> System.out.println("Invalid option.");
                }
            } catch (Exception e) {
                System.out.println("❌ " + e.getMessage());
            }
        }
    }
}
