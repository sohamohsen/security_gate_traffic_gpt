package com.research.ui;

import com.research.service.GatePassService;

import static com.research.util.InputUtil.readInt;


public class TrafficMenu {

    private final GatePassService gatePassService;

    public TrafficMenu(GatePassService gatePassService) {
        this.gatePassService = gatePassService;
    }

    public void show() {
        while (true) {
            System.out.println("\n--- Traffic Management ---");
            System.out.println("1. Approve Pass");
            System.out.println("2. Complete Pass");
            System.out.println("0. Back");

            try {
                switch (readInt("Choose: ")) {
                    case 1 -> gatePassService.approvePass(readInt("Pass ID: "));
                    case 2 -> gatePassService.completePass(readInt("Pass ID: "));
                    case 0 -> { return; }
                    default -> System.out.println("Invalid option.");
                }
            } catch (Exception e) {
                System.out.println("❌ " + e.getMessage());
            }
        }
    }
}
